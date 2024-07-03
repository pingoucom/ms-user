package com.pingou.msuser.application.cases.auth.sign_in;

import com.pingou.msuser.domain.entity.Token;
import com.pingou.msuser.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Output {
    private Token token;

    private User user;
}
