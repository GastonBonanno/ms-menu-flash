package com.project.menuflash.service.user;

import com.project.menuflash.controller.StateController;
import com.project.menuflash.dto.request.LoginUserDto;
import com.project.menuflash.dto.response.LoginUserResponse;
import com.project.menuflash.entity.ClientUserEntity;
import com.project.menuflash.jwt.TokenService;
import com.project.menuflash.mapper.UserMapper;
import com.project.menuflash.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;

@Service
public class UserServiceImpl implements UserService {

    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);
    private final UserRepository userRepository;

    private final TokenService tokenService;

    public UserServiceImpl(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
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

}
