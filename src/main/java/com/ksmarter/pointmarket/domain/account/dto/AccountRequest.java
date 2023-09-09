package com.ksmarter.pointmarket.domain.account.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record AccountRequest() {

    @Builder
    public record Join(
            @NotNull
            @Size(min = 3, max = 50)
            String name,

            @NotNull
            @Size(min = 3, max = 50)
            String userid,

            @NotNull
            @Size(min = 3, max = 50)
            String password
    ) {

    }

}
