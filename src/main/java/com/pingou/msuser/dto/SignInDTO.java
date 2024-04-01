package com.pingou.msuser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDTO {
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("password")
    private String password;
}
