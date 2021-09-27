package com.ls.validator.validators.impl;

import com.ls.validator.annotations.Max;
import com.ls.validator.annotations.Validate;
import com.ls.validator.message.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MaxValidatorTest {

    private MaxValidator maxValidator;

    private Max max;

    @BeforeEach
    void setUp() {
        maxValidator = new MaxValidator();
        max = new Max() {

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
                return "max error";
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
            maxValidator.apply(null, null);
            Assertions.fail("not IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {

        }

        Optional<Message> err2 = maxValidator.apply("20", max);
        Assertions.assertTrue(err2.isPresent());

        Optional<Message> err3 = maxValidator.apply("18a", max);
        Assertions.assertTrue(err3.isPresent());

        Optional<Message> success = maxValidator.apply("17", max);
        Assertions.assertTrue(success.isEmpty());

    }
}