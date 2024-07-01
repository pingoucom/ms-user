package com.pingou.msuser.application.cases.auth.sign_up;

import com.pingou.msuser.domain.entity.Token;
import com.pingou.msuser.domain.entity.User;
import lombok.Getter;

@Getter
public class Output {
    private Token token;

    private User user;
}
