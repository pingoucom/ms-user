package com.pingou.msuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Email is taken")
public class EmailIsTakenException extends IllegalArgumentException {
}
