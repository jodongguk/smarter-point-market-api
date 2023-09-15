package com.ksmarter.pointmarket.domain.common.enums.converter;

import com.ksmarter.pointmarket.domain.common.enums.BadgeTypes;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class BadgeTypesConverter implements AttributeConverter<BadgeTypes, String> {
    @Override
    public String convertToDatabaseColumn(BadgeTypes attribute) {
        return attribute.getCode();
    }

    @Override
    public BadgeTypes convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(BadgeTypes.class).stream()
                .filter(e -> e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
