package com.ksmarter.pointmarket.security.jwt.exception;

import com.ksmarter.pointmarket.security.jwt.enums.ErrorCode;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException(){
        super(ErrorCode.INVALID_REFRESH_TOKEN.getMessage());
    }
}
