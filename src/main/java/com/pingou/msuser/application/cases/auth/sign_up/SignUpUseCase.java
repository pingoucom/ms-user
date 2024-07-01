package com.pingou.msuser.application.cases.auth.sign_up;

import com.pingou.msuser.domain.service.UserService;

public class SignUpUseCase {
    private final UserService userService;

    public SignUpUseCase(UserService userService) {
        this.userService = userService;
    }

    public Output execute(Input input) {
        return null;
    }
}
