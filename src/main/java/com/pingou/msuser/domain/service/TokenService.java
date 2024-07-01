package com.pingou.msuser.domain.service;

import com.pingou.msuser.domain.entity.Token;
import com.pingou.msuser.domain.entity.User;
import com.pingou.msuser.domain.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token create(User user) {
        return tokenRepository.create(user);
    }
}
