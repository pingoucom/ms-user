package com.pingou.msuser.domain.hash;

public interface Hasher {
    String hash(String plainPassword);

    boolean verify(String plainPassword, String hashedPassword);
}
