package com.ksmarter.pointmarket.domain.common.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String code,
        String message,
        int status
) {
}
