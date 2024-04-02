package com.pingou.msuser.infrastructure.hash;

import com.pingou.msuser.domain.hash.Hasher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptHasher implements Hasher {
    private final int workload = 12;

    private BCryptPasswordEncoder encoder;

    BcryptHasher() {
        encoder = new BCryptPasswordEncoder(workload);
    }

    public String hash(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public boolean verify(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}
