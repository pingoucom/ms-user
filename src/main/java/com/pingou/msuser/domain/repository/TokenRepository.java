package com.pingou.msuser.domain.repository;

import com.pingou.msuser.domain.entity.Token;
import com.pingou.msuser.domain.entity.User;

public interface TokenRepository {
    Token create(User user);
}
