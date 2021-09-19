package com.ls.validator.ivalidators.impl;

import com.ls.validator.annotations.Max;
import com.ls.validator.ivalidators.IValidator;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class MaxValidator implements IValidator {

    @Override
    public Optional<String> apply(Object value, Annotation annotation) throws Exception {
        if (value != null && annotation instanceof Max max) {
            double v = Double.parseDouble(value.toString());
            if (v <= max.value()) {
                return Optional.empty();
            }
            return Optional.of(max.message());
        }
        throw new NoSuchMethodException();
    }

}
