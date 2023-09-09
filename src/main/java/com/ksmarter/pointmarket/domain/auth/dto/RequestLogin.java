package com.ksmarter.pointmarket.domain.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record RequestLogin() {
    @Builder
    public record Login(
            @NotNull
            @Size(min = 3, max = 50)
            String username,
            @NotNull
            @Size(min = 5, max = 100)
            String password
    ) {
    }

    @Builder
    public record Refresh(
            @NotNull
            String token
    ) {
    }
}
