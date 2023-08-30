package com.project.menuflash.dto.response;

import lombok.Data;

@Data
public class LoginUserResponse {
    private LoggedUser user;
    private String token;
}


