package com.ls.validator.validators.impl;

import com.ls.validator.annotations.MinDateTime;
import com.ls.validator.message.Message;
import com.ls.validator.validators.AbstractValidator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class MinDateTimeValidator extends AbstractValidator {

    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {
        if (value != null && annotation instanceof MinDateTime minDateTime) {
            Message message = Message.builder()
                    .key(minDateTime.key())
                    .msg(minDateTime.message())
                    .language(minDateTime.language())
                    .value(minDateTime.value() + "")
                    .build();

            LocalDateTime min = minDateTime.minIsNow() ? LocalDateTime.now()
                    : LocalDateTime.parse(minDateTime.value(), localDateTimeDf);

            LocalDateTime compareTime = null;

            if (value instanceof LocalDateTime v) {
                compareTime = v;
            } else if (value instanceof String compare) {
                try {
                    compareTime = LocalDateTime.parse(compare, localDateTimeDf);
                } catch (DateTimeParseException e) {
                    log.debug("DateTimeParseException", e);
                }
            } else {
                message.setDescription("field must String or LocalDateTime");
            }
            if (compareTime != null && (min.isBefore(compareTime) || min.isEqual(compareTime))) {
                return Optional.empty();
            }
            return Optional.of(message);
        }
        throw new IllegalArgumentException();
    }
}
