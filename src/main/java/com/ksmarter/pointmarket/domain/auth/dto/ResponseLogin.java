package com.ksmarter.pointmarket.domain.auth.dto;

import lombok.Builder;

public record ResponseLogin() {

    @Builder
    public record Token(
            String accessToken,
            String refreshToken
    ) {
    }
}
