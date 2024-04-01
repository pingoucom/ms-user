package com.pingou.msuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Incorrect password")
public class IncorrectPasswordException extends RuntimeException {
}
