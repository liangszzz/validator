package com.ls.validator.validators.impl;

import com.ls.validator.annotations.MinLocalDate;
import com.ls.validator.message.Message;
import com.ls.validator.validators.abs.DateTimeAbstractValidator;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
public class MinLocalDateValidator extends DateTimeAbstractValidator {
    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {

        if (value != null && annotation instanceof MinLocalDate minLocalDate) {

            LocalDate settingDate = null;
            if (minLocalDate.now()) {
                settingDate = LocalDate.now();
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(minLocalDate.format());
                    settingDate = LocalDate.parse(minLocalDate.value(), dtf);
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
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(minLocalDate.format());
                    inputDate = LocalDate.parse(v, dtf);
                } catch (RuntimeException e) {
                    log.debug("RuntimeException", e);
                }
            } else if (value instanceof LocalDate v) {
                inputDate = v;
            }

            if (inputDate != null && (settingDate.isBefore(inputDate) || settingDate.isEqual(inputDate))) {
                return Optional.empty();
            } else {
                return Optional.of(Message.builder()
                        .msg(minLocalDate.message())
                        .language(minLocalDate.language())
                        .key(minLocalDate.key())
                        .value(value + "")
                        .build());
            }


        }


        return Optional.empty();
    }
}
