package com.pingou.msuser.hash;

public interface Hasher {
    String hash(String plainPassword);

    boolean verify(String plainPassword, String hashedPassword);
}
