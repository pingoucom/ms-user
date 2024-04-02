package com.pingou.msuser.presentation.http.controller;

import com.pingou.msuser.application.dto.SignInDTO;
import com.pingou.msuser.application.dto.SignUpDTO;
import com.pingou.msuser.domain.entity.User;
import com.pingou.msuser.application.dto.UserDTO;
import com.pingou.msuser.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public UserDTO signIn(@Valid @RequestBody SignInDTO signInDTO) {
        User user = userService.signIn(signInDTO.getEmail(), signInDTO.getPassword());

        return new UserDTO(user);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public UserDTO signUp(@Valid @RequestBody SignUpDTO signUpDTO) {
        User newUser = userService.signUp(signUpDTO.toUser());

        return new UserDTO(newUser);
    }
}
