package com.ksmarter.pointmarket.domain.common.enums.converter;

import com.ksmarter.pointmarket.domain.common.enums.AssignmentSubmitTypes;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class AssignmentSubmitTypesConverter implements AttributeConverter<AssignmentSubmitTypes, String> {
    @Override
    public String convertToDatabaseColumn(AssignmentSubmitTypes attribute) {
        return attribute.getCode();
    }

    @Override
    public AssignmentSubmitTypes convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(AssignmentSubmitTypes.class).stream()
                .filter(e -> e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
