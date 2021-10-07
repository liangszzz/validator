package com.ls.validator.validators.impl;

import com.ls.validator.annotations.MaxLocalDate;
import com.ls.validator.message.Message;
import com.ls.validator.validators.abs.DateTimeAbstractValidator;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
public class MaxLocalDateValidator extends DateTimeAbstractValidator {

    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {
        if (value != null && annotation instanceof MaxLocalDate maxLocalDate) {

            LocalDate settingDate = null;

            if (maxLocalDate.now()) {
                settingDate = LocalDate.now();
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(maxLocalDate.format());
                    settingDate = LocalDate.parse(maxLocalDate.value(), dtf);
                } catch (RuntimeException e) {
                    log.debug("RuntimeException", e);
                }
            }

            if (settingDate == null) {
                throw new IllegalArgumentException();
            }

            LocalDate inputDate = null;

            if (value instanceof String v) {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(maxLocalDate.format());
                    inputDate = LocalDate.parse(v, dtf);
                } catch (RuntimeException e) {
                    log.debug("RuntimeException", e);
                }
            } else if (value instanceof LocalDate v) {
                inputDate = v;
            }

            if (inputDate != null && settingDate.isAfter(inputDate)) {
                return Optional.empty();
            } else {

                return Optional.of(Message.builder()
                        .msg(maxLocalDate.message())
                        .language(maxLocalDate.language())
                        .key(maxLocalDate.key())
                        .value(value + "")
                        .build());
            }
        }

        throw new IllegalArgumentException();
    }
}
