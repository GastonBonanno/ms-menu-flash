package com.project.menuflash.controller;

import com.project.menuflash.dto.request.LoginUserDto;
import com.project.menuflash.dto.request.RegisterUserDto;
import com.project.menuflash.dto.response.LoginUserResponse;
import com.project.menuflash.service.user.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path="/user")
public class UserController {
    private final UserService userService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserDto loginUserDto) throws Exception {
        LOG.info("Login begins");
        LoginUserResponse response = userService.loginUser(loginUserDto);
        LOG.info("Finished");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(path = "/register")
    public ResponseEntity<?> create(@RequestBody RegisterUserDto registerUserDto) throws Exception {
        LOG.info("Login begins");
        userService.registerUser(registerUserDto);
        LOG.info("Finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("auth-token") String authToken) throws Exception {
        LOG.info("validateToken begins");
        Boolean response = userService.validateToken(authToken);
        LOG.info("Finished validateToken");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
