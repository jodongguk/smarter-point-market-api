package com.ksmarter.pointmarket.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor
public enum AuthorityRoles {

    SYSTEM("SYSTEM", "ROLE_SYSTEM"),
    ADMIN("ADMIN", "ROLE_ADMIN"),
    FRANCHISOR("FRANCHISOR", "ROLE_FRANCHISOR"),
    INSTITUTE("INSTITUTE", "ROLE_INSTITUTE"),
    PARENT("PARENT", "ROLE_PARENT"),
    CHILD("CHILD", "ROLE_CHILD"),
    USER("USER", "ROLE_USER"),
    ANONYMOUS("ANONYMOUS", "ROLE_ANONYMOUS");

    private String code;
    private String value;

    public static AuthorityRoles of(String code) {
        return EnumSet.allOf(AuthorityRoles.class).stream()
                .filter(e -> e.getCode().equals(code.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
