package com.ksmarter.pointmarket.domain.common.enums.converter;

import com.ksmarter.pointmarket.domain.common.enums.UsedTypes;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class UsedTypesConverter implements AttributeConverter<UsedTypes, String> {
    @Override
    public String convertToDatabaseColumn(UsedTypes attribute) {
        return attribute.getCode();
    }

    @Override
    public UsedTypes convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(UsedTypes.class).stream()
                .filter(e -> e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
