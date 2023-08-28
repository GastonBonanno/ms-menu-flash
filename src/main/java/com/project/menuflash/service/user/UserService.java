package com.project.menuflash.service.user;

import com.project.menuflash.dto.request.CreateStateDto;
import com.project.menuflash.dto.request.LoginUserDto;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.FindAllStateResponse;
import com.project.menuflash.dto.response.LoginUserResponse;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface UserService {
  LoginUserResponse loginUser(LoginUserDto loginUserDto) throws ResponseStatusException;

  LoginUserResponse validateToken(String authToken) throws Exception;

}
