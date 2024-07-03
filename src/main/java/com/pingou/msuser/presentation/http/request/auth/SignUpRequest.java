package com.pingou.msuser.presentation.http.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    private String name;

    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
