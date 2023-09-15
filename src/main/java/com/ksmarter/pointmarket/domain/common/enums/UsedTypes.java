package com.ksmarter.pointmarket.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsedTypes {

    USED("Y", "사용"),
    NOT_USED("N", "사용안함");

    private String code;
    private String value;
}
