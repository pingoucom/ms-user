package com.pingou.msuser.hash;

import org.springframework.beans.factory.annotation.Value;
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
