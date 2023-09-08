package com.ksmarter.pointmarket.domain.account.common.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String code,
        String message,
        int status
) {
}
