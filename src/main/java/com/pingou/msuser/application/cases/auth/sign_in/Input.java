package com.pingou.msuser.application.cases.auth.sign_in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Input {
    private String email;

    private String password;
}
