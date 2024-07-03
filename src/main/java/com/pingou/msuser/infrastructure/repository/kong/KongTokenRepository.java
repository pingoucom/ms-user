package com.pingou.msuser.infrastructure.repository.kong;

import com.pingou.msuser.domain.entity.Token;
import com.pingou.msuser.domain.entity.User;
import com.pingou.msuser.domain.repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Component
public class KongTokenRepository implements TokenRepository {
    private String tokenType = "bearer";

    @Value("${kong.base_uri}")
    private String baseUri;

    private Integer duration = 3600;

    private final RestClient restClient;

    public KongTokenRepository() {
        this.restClient = RestClient.builder().build();
    }

    public String createConsumer(User user) {
        String uri = baseUri + "/consumers";

        CreateConsumerResponse response = restClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("custom_id", user.getId()))
                .retrieve()
                .body(CreateConsumerResponse.class);

        return response.getId();
    }

    @Override
    public Token createToken(User user) {
        String uri = baseUri + "/consumers/" + user.getConsumerId() + "/jwt";

        CreateTokenResponse response = restClient.post()
                .uri(uri)
                .retrieve()
                .body(CreateTokenResponse.class);

        LocalDateTime expiration = LocalDateTime.now().plusSeconds(duration);

        byte[] keyBytes = response.getSecret().getBytes();
        Key key = Keys.hmacShaKeyFor(keyBytes);

        String digest = Jwts.builder()
                .subject(user.getId())
                .issuer(response.getKey())
                .expiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();

        return new Token(
                tokenType,
                digest,
                expiration
        );
    }
}
