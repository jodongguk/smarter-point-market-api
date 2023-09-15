package com.ksmarter.pointmarket.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BadgeTypes {

    COMPLIMENT("COMPLIMENT", "칭찬");

    private String code;
    private String value;
}
