package com.pingou.msuser.presentation.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse {
    private TokenResponse token;

    private UserResponse user;
}
