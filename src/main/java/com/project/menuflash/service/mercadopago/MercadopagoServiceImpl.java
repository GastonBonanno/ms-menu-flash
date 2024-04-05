package com.project.menuflash.service.mercadopago;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;
import com.mercadopago.MercadoPagoConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class MercadopagoServiceImpl implements MercadopagoService {

    @Override
    public String createPreference() throws Exception {
        MercadoPagoConfig.setAccessToken("TEST-4920957456022129-031812-ad26a00ade5dd917fcef0a65e7897cad-140668497");

        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                                            .id("1234")
                                            .title("Games")
                                            .description("PS5")
                                            .pictureUrl("http://picture.com/PS5")
                                            .categoryId("games")
                                            .quantity(2)
                                            .currencyId("BRL")
                                            .unitPrice(new BigDecimal("4000"))
                                            .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .build();

        Preference preference = null;
        try {
            PreferenceClient client = new PreferenceClient();
            preference = client.create(preferenceRequest);
        } catch (Exception e) {
            System.out.println("Exception en el pagooo: " + e.getMessage());
        }
        System.out.println("preference.getId(): " + preference.getId());

        return preference.getId();
    }
}
