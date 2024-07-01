package com.pingou.msuser.domain.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Token {
    private String type;

    private String digest;

    private LocalDateTime expiresAt;
}
