package com.ls.validator.validators.impl;

import com.ls.validator.annotations.Min;
import com.ls.validator.annotations.Validate;
import com.ls.validator.message.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MinValidatorTest {

    private MinValidator minValidator;

    private Min min;

    @BeforeEach
    void setUp() {
        minValidator = new MinValidator();
        min = new Min() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Validate.class;
            }

            @Override
            public double value() {
                return 18;
            }

            @Override
            public String message() {
                return "${field} must be greater than or equal to ${value}";
            }

            @Override
            public String language() {
                return "";
            }

            @Override
            public boolean key() {
                return false;
            }
        };
    }

    @Test
    void apply() {

        try {
            minValidator.apply(null, null);
            Assertions.fail("not IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {

        }

        Optional<Message> err2 = minValidator.apply("18", min);
        Assertions.assertTrue(err2.isPresent());

        Optional<Message> err3 = minValidator.apply("18a", min);
        Assertions.assertTrue(err3.isPresent());

        Optional<Message> success = minValidator.apply("20", min);
        Assertions.assertTrue(success.isEmpty());
    }
}