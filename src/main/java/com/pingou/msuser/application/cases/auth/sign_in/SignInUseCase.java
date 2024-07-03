package com.pingou.msuser.application.cases.auth.sign_in;

import com.pingou.msuser.domain.entity.Token;
import com.pingou.msuser.domain.entity.User;
import com.pingou.msuser.domain.service.TokenService;
import com.pingou.msuser.domain.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class SignInUseCase {
    private final UserService userService;

    private final TokenService tokenService;

    public SignInUseCase(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public Output execute(Input input) {
        User user = userService.signIn(input.getEmail(), input.getPassword());
        Token token = tokenService.create(user);

        return new Output(token, user);
    }
}
