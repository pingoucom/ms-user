package com.pingou.msuser.infrastructure.repository.kong;

import lombok.Getter;

@Getter
public class CreateTokenResponse {
    private String key;

    private String secret;
}
