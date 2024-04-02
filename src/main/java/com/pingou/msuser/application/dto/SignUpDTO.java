package com.pingou.msuser.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pingou.msuser.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
    @NotBlank
    @JsonProperty("name")
    private String name;

    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("password")
    private String password;

    public User toUser() {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        return newUser;
    }
}
