package com.ls.validator.ivalidators.impl;

import com.ls.validator.annotations.Min;
import com.ls.validator.ivalidators.IValidator;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class MinValidator implements IValidator {

    @Override
    public Optional<String> apply(Object value, Annotation annotation) throws Exception {
        if (value != null && annotation instanceof Min min) {
            double v = Double.parseDouble(value.toString());
            if (v > min.value()) {
                return Optional.empty();
            }
            return Optional.of(min.message());
        }
        throw new NoSuchMethodException();
    }
}
