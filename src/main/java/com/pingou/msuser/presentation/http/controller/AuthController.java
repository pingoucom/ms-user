package com.pingou.msuser.presentation.http.controller;

import com.pingou.msuser.presentation.http.request.auth.SignInRequest;
import com.pingou.msuser.presentation.http.request.auth.SignUpRequest;
import com.pingou.msuser.presentation.http.response.AuthenticationResponse;
import com.pingou.msuser.presentation.http.response.TokenResponse;
import com.pingou.msuser.presentation.http.response.UserResponse;
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
    private final com.pingou.msuser.application.cases.auth.sign_in.SignInUseCase signInUseCase;

    private final com.pingou.msuser.application.cases.auth.sign_up.SignUpUseCase signUpUseCase;

    public AuthController(
            com.pingou.msuser.application.cases.auth.sign_in.SignInUseCase signInUseCase,
            com.pingou.msuser.application.cases.auth.sign_up.SignUpUseCase signUpUseCase
    ) {
        this.signInUseCase = signInUseCase;
        this.signUpUseCase = signUpUseCase;
    }

    @ResponseBody
    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public AuthenticationResponse signIn(@Valid @RequestBody SignInRequest request) {
        com.pingou.msuser.application.cases.auth.sign_in.Output output = signInUseCase.execute(
                new com.pingou.msuser.application.cases.auth.sign_in.Input(request.getEmail(), request.getPassword())
        );

        return new AuthenticationResponse(new TokenResponse(output.getToken()), new UserResponse(output.getUser()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public AuthenticationResponse signUp(@Valid @RequestBody SignUpRequest request) {
        com.pingou.msuser.application.cases.auth.sign_up.Output output = signUpUseCase.execute(
                new com.pingou.msuser.application.cases.auth.sign_up.Input(request.getName(), request.getEmail(), request.getPassword())
        );

        return new AuthenticationResponse(new TokenResponse(output.getToken()), new UserResponse(output.getUser()));
    }
}
