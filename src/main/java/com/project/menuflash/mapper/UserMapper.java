package com.project.menuflash.mapper;


import com.project.menuflash.dto.request.RegisterUserDto;
import com.project.menuflash.dto.response.LoggedUser;
import com.project.menuflash.dto.response.LoginUserResponse;
import com.project.menuflash.entity.ClientUserEntity;

public class UserMapper {

    public static LoginUserResponse entityToResponse(ClientUserEntity clientUserEntity){
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        LoggedUser user = new LoggedUser();
        user.setEmail(clientUserEntity.getEmail());
        user.setId(clientUserEntity.getId());
        loginUserResponse.setUser(user);
        return loginUserResponse;
    }

    public static ClientUserEntity dtoToEntity(RegisterUserDto registerUserDto){
        ClientUserEntity clientUserEntity = new ClientUserEntity();
        clientUserEntity.setEmail(registerUserDto.getEmail());
        clientUserEntity.setPassword(registerUserDto.getPassword());
        return clientUserEntity;
    }
}
