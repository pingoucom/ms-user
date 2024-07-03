package com.pingou.msuser.presentation.http.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pingou.msuser.domain.entity.Token;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TokenResponse {
    private final String type;

    private final String digest;

    @JsonProperty("expires_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private final LocalDateTime expiresAt;

    public TokenResponse(Token token) {
        this.type = token.getType();
        this.digest = token.getDigest();
        this.expiresAt = token.getExpiresAt();
    }
}
