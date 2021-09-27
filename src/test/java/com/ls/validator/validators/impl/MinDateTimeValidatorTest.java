package com.ls.validator.validators.impl;

import com.ls.validator.annotations.MinDateTime;
import com.ls.validator.annotations.Validate;
import com.ls.validator.message.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MinDateTimeValidatorTest {

    private MinDateTimeValidator minDateTimeValidator;

    private MinDateTime minDateTime;

    private MinDateTime minDateTime2;

    @BeforeEach
    void setUp() {
        minDateTimeValidator = new MinDateTimeValidator();
        minDateTime = new MinDateTime() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return Validate.class;
            }

            @Override
            public String value() {
                return "2020-01-01 01:01:01";
            }

            @Override
            public String message() {
                return "minDateTime error";
            }

            @Override
            public String language() {
                return "";
            }

            @Override
            public boolean key() {
                return false;
            }

            @Override
            public boolean minIsNow() {
                return false;
            }
        };
        minDateTime2 = new MinDateTime() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return Validate.class;
            }

            @Override
            public String value() {
                return "2021-01-01";
            }

            @Override
            public String message() {
                return "minDateTime error";
            }

            @Override
            public String language() {
                return "";
            }

            @Override
            public boolean key() {
                return false;
            }

            @Override
            public boolean minIsNow() {
                return true;
            }
        };
    }

    @Test
    void apply() {

        try {
            minDateTimeValidator.apply(null, null);
        } catch (IllegalArgumentException ignore) {

        }

        Optional<Message> err2 = minDateTimeValidator.apply(LocalDateTime.of(2019, 1, 1, 1, 1, 1), minDateTime);
        Assertions.assertTrue(err2.isPresent());

        Optional<Message> success = minDateTimeValidator.apply(LocalDateTime.of(2020, 1, 1, 1, 1, 1), minDateTime);
        Assertions.assertTrue(success.isEmpty());

        Optional<Message> err3 = minDateTimeValidator.apply("2019-01-01 01:01:01", minDateTime);
        Assertions.assertTrue(err3.isPresent());

        Optional<Message> err4 = minDateTimeValidator.apply("2019a-01-01 01:01:01", minDateTime);
        Assertions.assertTrue(err4.isPresent());

        Optional<Message> success2 = minDateTimeValidator.apply("2020-01-01 01:01:01", minDateTime);
        Assertions.assertTrue(success2.isEmpty());

        Optional<Message> success3 = minDateTimeValidator.apply("2023-01-01 01:01:01", minDateTime2);
        Assertions.assertTrue(success3.isEmpty());

        Optional<Message> err5 = minDateTimeValidator.apply("2019-01-01 01:01:01", minDateTime2);
        Assertions.assertTrue(err5.isPresent());

        Optional<Message> err6 = minDateTimeValidator.apply(18, minDateTime2);
        Assertions.assertTrue(err6.isPresent());
    }
}