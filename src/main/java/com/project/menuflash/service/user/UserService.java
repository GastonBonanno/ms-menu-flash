package com.project.menuflash.service.user;

import com.project.menuflash.dto.request.*;
import com.project.menuflash.dto.response.CompanyDataResponse;
import com.project.menuflash.dto.response.FindAllStateResponse;
import com.project.menuflash.dto.response.LoginUserResponse;
import org.apache.catalina.connector.Response;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface UserService {
  LoginUserResponse loginUser(LoginUserDto loginUserDto) throws ResponseStatusException;

  Boolean validateToken(String authToken) throws Exception;

  void registerUser(RegisterUserDto registerUserDto);

  CompanyDataResponse getCompanyData(String authToken) throws Exception;

  void updateCompanyData (String authToken,UpdateCompanyDataDto updateCompanyDataDto) throws Exception;
}
