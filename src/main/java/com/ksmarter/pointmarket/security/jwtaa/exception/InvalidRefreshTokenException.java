package com.ksmarter.pointmarket.security.jwtaa.exception;

import com.ksmarter.pointmarket.security.jwtaa.enums.ErrorCode;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException(){
        super(ErrorCode.INVALID_REFRESH_TOKEN.getMessage());
    }
}
