package com.ksmarter.pointmarket.security.jwt.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    REQUEST_PARAMETER_BIND_FAILED(HttpStatus.BAD_REQUEST, "REQ_001", "PARAMETER_BIND_FAILED"),
    DUPLICATE_MEMBER_EXCEPTION(HttpStatus.CONFLICT, "AUTH_001", "회원 중복"),
    INVALID_REFRESH_TOKEN(HttpStatus.FORBIDDEN, "AUTH_002", "리프레시 토큰이 유효하지 않습니다");

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
