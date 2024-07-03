package com.pingou.msuser.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Token {
    private String type;

    private String digest;

    private LocalDateTime expiresAt;
}
