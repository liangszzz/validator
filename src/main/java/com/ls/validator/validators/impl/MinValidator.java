package com.ls.validator.validators.impl;

import com.ls.validator.annotations.Min;
import com.ls.validator.message.Message;
import com.ls.validator.validators.AbstractValidator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class MinValidator extends AbstractValidator {

    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {
        if (value != null && annotation instanceof Min min) {
            double v;
            try {
                v = Double.parseDouble(value.toString());
                if (v >= min.value()) {
                    return Optional.empty();
                }
            } catch (NumberFormatException e) {
                log.debug("parseDouble exception", e);
            }
            Message message = Message.builder()
                    .key(min.key())
                    .msg(min.message())
                    .language(min.language())
                    .value(min.value() + "")
                    .build();
            return Optional.of(message);
        }
        throw new IllegalArgumentException();
    }
}
