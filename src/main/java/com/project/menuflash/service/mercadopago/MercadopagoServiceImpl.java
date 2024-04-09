package com.project.menuflash.service.mercadopago;

import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;
import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.MercadopagoDto;
import com.project.menuflash.dto.response.CompanyDataResponse;
import com.project.menuflash.entity.ItemMenuEntity;
import com.project.menuflash.service.item_menu.ItemMenuService;
import com.project.menuflash.service.user.UserService;
import org.springframework.stereotype.Service;
import com.mercadopago.MercadoPagoConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class MercadopagoServiceImpl implements MercadopagoService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    private final ItemMenuService itemMenuService;
    private final UserService userService;

    public MercadopagoServiceImpl(ItemMenuService itemMenuService, UserService userService) {
        this.itemMenuService = itemMenuService;
        this.userService = userService;
    }

    @Override
    public String createPreference(List<MercadopagoDto> mercadopagoDto, Long companyId, Long orderId) throws Exception {

        BigDecimal totalAmount = getTotalAmount(mercadopagoDto);
        if(totalAmount.equals(BigDecimal.ZERO)) {
            LOG.error("Error al contabilizar los montos");
            throw new Exception("Error al contabilizar los montos");
        }

        MercadoPagoConfig.setAccessToken(getAccessToken(companyId));

        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                                            .id("1234")
                                            .title("Restaurant/Bar")
                                            .description("Restaurant/Bar")
                                            .quantity(1)
                                            .currencyId("ARS")
                                            .unitPrice(totalAmount)
                                            .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

        PreferencePaymentMethodsRequest paymentMethodRequest = PreferencePaymentMethodsRequest.builder()
                .excludedPaymentTypes(excludedPaymentTypes)
                .build();

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
//                .success("http://localhost:8100/afterPayment?orderId="+orderId)
//                .failure("http://localhost:8100/scanQr")
//                .pending("http://localhost:8100/scanQr")
                .success("http://menuflashapp/?orderId="+orderId)
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .paymentMethods(paymentMethodRequest)
                .backUrls(backUrls)
                .build();

        Preference preference = null;
        try {
            PreferenceClient client = new PreferenceClient();
            preference = client.create(preferenceRequest);
            return preference.getId();
        } catch (Exception e) {
            LOG.error("Error en el pago: " + e.getMessage());
        }
        return null;
    }

    private String getAccessToken(Long companyId) throws Exception {
        CompanyDataResponse companyData = userService.getCompanyData(companyId);
        return companyData.getAccessToken();
    }

    private BigDecimal getTotalAmount(List<MercadopagoDto> mercadopagoDto) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for(MercadopagoDto dto : mercadopagoDto) {
            ItemMenuEntity item;
            try {
                item = itemMenuService.getItemMenuEntityById(dto.getItemId());
                totalAmount = totalAmount.add(item.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity())));
            } catch (Exception e) {
                LOG.error("Error al obtener items: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return totalAmount;
    }
}
