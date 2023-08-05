package com.project.menuflash.dto.response;

import lombok.Data;

@Data
public class LoginUserResponse {
    private LoginUser user;
    private String token;
}


