package com.project.menuflash.service.user;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.LoginUserDto;
import com.project.menuflash.dto.request.RegisterUserDto;
import com.project.menuflash.dto.response.CompanyDataResponse;
import com.project.menuflash.dto.response.LoginUserResponse;
import com.project.menuflash.entity.ClientUserEntity;
import com.project.menuflash.entity.CompanyDataEntity;
import com.project.menuflash.jwt.TokenService;
import com.project.menuflash.mapper.CompanyDataMapper;
import com.project.menuflash.mapper.UserMapper;
import com.project.menuflash.repository.CompanyDataRepository;
import com.project.menuflash.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);
    private final UserRepository userRepository;
    private final CompanyDataRepository companyDataRepository;

    private final TokenService tokenService;

    public UserServiceImpl(UserRepository userRepository, TokenService tokenService, CompanyDataRepository companyDataRepository) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.companyDataRepository = companyDataRepository;
    }

    public LoginUserResponse loginUser(LoginUserDto loginUserDto) throws ResponseStatusException {
        try {
            ClientUserEntity clientUserEntity = userRepository.findByEmail(loginUserDto.getEmail());
            validateUserAndPassword(loginUserDto.getPassword(), clientUserEntity);
            LoginUserResponse loginUserResponse = UserMapper.entityToResponse(clientUserEntity);
            loginUserResponse.setToken(tokenService.getJwtToken(loginUserResponse.getUser()));
            return loginUserResponse;
        } catch (Exception e) {
            LOG.error("loginUser error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al loguear", e);
        }
    }

    private void validateUserAndPassword(String pass, ClientUserEntity clientUserEntity) throws Exception {
        String errorMessage = "Invalid User/Password";
        if(clientUserEntity == null)
            throw new Exception(errorMessage);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        if(clientUserEntity.getPassword() != null && !bCryptPasswordEncoder.matches(pass, clientUserEntity.getPassword()))
            throw new Exception(errorMessage);
    }

    public Boolean validateToken(String authToken) throws ResponseStatusException {
        try {
            tokenService.getUserFromToken(authToken);
            return Boolean.TRUE;
        } catch (Exception e) {
            LOG.error("validateToken error: {}", e.getMessage());
        }
        return Boolean.FALSE;
    }

    @Override
    public void registerUser(RegisterUserDto registerUserDto) {
        try {
            String passEncrypted = hashPassword(registerUserDto.getPassword());
            registerUserDto.setPassword(passEncrypted);
            ClientUserEntity clientUser = UserMapper.dtoToEntity(registerUserDto);
            clientUser.setActive(Boolean.TRUE);
            clientUser.setCreatedAt(new Date());
            ClientUserEntity clientUserSaved = userRepository.save(clientUser);
            CompanyDataEntity companyDataEntity = new CompanyDataEntity();
            companyDataEntity.setActive(Boolean.TRUE);
            companyDataEntity.setCreatedAt(new Date());
            companyDataEntity.setClientUserEntity(clientUserSaved);
            companyDataRepository.save(companyDataEntity);
        } catch (Exception e) {
            LOG.error("registerUser error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear usuario", e);
        }
    }

    public CompanyDataResponse getCompanyData(String authToken) throws Exception {
        Long id = tokenService.getUserFromToken(authToken).getId();
        CompanyDataEntity companyDataEntity = companyDataRepository.findByClientUserId(id);
        return CompanyDataMapper.entityToResponse(companyDataEntity);
    }

    private String hashPassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        return bCryptPasswordEncoder.encode(password);
    }

}
