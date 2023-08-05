package com.project.menuflash.mapper;


import com.project.menuflash.dto.response.LoginUser;
import com.project.menuflash.dto.response.LoginUserResponse;
import com.project.menuflash.entity.ClientUserEntity;

public class UserMapper {

    public static LoginUserResponse entityToResponse(ClientUserEntity clientUserEntity){
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        LoginUser user = new LoginUser();
        user.setName(clientUserEntity.getCompanyDataEntity().getName());
        user.setEmail(clientUserEntity.getEmail());
        user.setCuit(clientUserEntity.getCompanyDataEntity().getCuit());
        user.setAddress(clientUserEntity.getCompanyDataEntity().getAddress());
        user.setPhoneNumber(clientUserEntity.getCompanyDataEntity().getPhoneNumber());
        loginUserResponse.setUser(user);
        return loginUserResponse;
    }
}
