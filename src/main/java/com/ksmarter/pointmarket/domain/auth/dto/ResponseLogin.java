package com.ksmarter.pointmarket.domain.auth.dto;

import lombok.Builder;

public record ResponseLogin() {

    @Builder
    public record Token(
            String grantType,
            String accessToken,
            Long accessTokenExpirationTime,
            String refreshToken,
            Long refreshTokenExpirationTime
    ) {
    }
}
