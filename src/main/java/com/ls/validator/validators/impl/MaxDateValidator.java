package com.ls.validator.validators.impl;

import com.ls.validator.annotations.MaxDate;
import com.ls.validator.message.Message;
import com.ls.validator.validators.abs.DateTimeAbstractValidator;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class MaxDateValidator extends DateTimeAbstractValidator {
    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {
        if (value != null && annotation instanceof MaxDate maxDate) {
            Date settingDate = null;
            if (maxDate.now()) {
                settingDate = new Date();
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(maxDate.format());
                try {
                    settingDate = sdf.parse(maxDate.value());
                } catch (ParseException e) {
                    log.debug("ParseException", e);
                }
            }
            if (settingDate == null) {
                throw new IllegalArgumentException();
            }

            Date inputDate = null;

            if (value instanceof String v) {
                SimpleDateFormat sdf = new SimpleDateFormat(maxDate.format());
                try {
                    inputDate = sdf.parse(v);
                } catch (ParseException e) {
                    log.debug("ParseException", e);
                }
            } else if (value instanceof Date v) {
                inputDate = v;
            }

            if (settingDate.compareTo(inputDate) >= 0) {
                return Optional.empty();
            }

            return Optional.of(Message.builder()
                    .key(maxDate.key())
                    .msg(maxDate.message())
                    .language(maxDate.language())
                    .value(maxDate.value() + "")
                    .build());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Set<Class<?>> supportedTypes() {
        Set<Class<?>> set = super.supportedTypes();
        set.add(Date.class);
        return set;
    }
}
