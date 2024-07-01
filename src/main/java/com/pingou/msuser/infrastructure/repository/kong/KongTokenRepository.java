package com.pingou.msuser.infrastructure.repository.kong;

import com.pingou.msuser.domain.entity.Token;
import com.pingou.msuser.domain.entity.User;
import com.pingou.msuser.domain.repository.TokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class KongTokenRepository implements TokenRepository {
    @Value("${kong.host}")
    private String baseUri;

    @Value("${kong.jwt.duration}")
    private Integer duration;

    private final RestClient restClient;

    public KongTokenRepository(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public Token create(User user) {
        String uri = baseUri + "/consumers/" + user.getId() + "/jwt";

        CreateTokenResponse response = restClient.post()
                .uri(uri)
                .retrieve()
                .body(CreateTokenResponse.class);

        LocalDateTime expiration = LocalDateTime.now().plusSeconds(duration);

        String digest = Jwts.builder()
                .setSubject(user.getId())
                .setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(Jwts.KEY, SignatureAlgorithm.HS256);

        return new Token(
        );
    }
}
