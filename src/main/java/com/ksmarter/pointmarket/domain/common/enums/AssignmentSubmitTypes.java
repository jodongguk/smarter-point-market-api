package com.ksmarter.pointmarket.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor
public enum AssignmentSubmitTypes {

    STANDBY("STANDBY", "대기"),
    PENDING("PENDING", "보류"),
    REJECT("REJECT", "반려"),
    SUCCESS("SUCCESS", "승인");

    private String code;
    private String value;

    public static AssignmentSubmitTypes of(String code) {
        return EnumSet.allOf(AssignmentSubmitTypes.class).stream()
                .filter(e -> e.getCode().equals(code.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
