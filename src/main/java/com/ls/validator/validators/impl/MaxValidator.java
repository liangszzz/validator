package com.ls.validator.validators.impl;

import com.ls.validator.annotations.Max;
import com.ls.validator.message.Message;
import com.ls.validator.validators.abs.DefaultAbstractValidator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class MaxValidator extends DefaultAbstractValidator {

    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {
        if (value != null && annotation instanceof Max max) {
            double v;
            try {
                v = Double.parseDouble(value.toString());
                if (v < max.value()) {
                    return Optional.empty();
                }
            } catch (NumberFormatException e) {
                log.debug("parseDouble exception", e);
            }

            Message message = Message.builder()
                    .key(max.key())
                    .msg(max.message())
                    .language(max.language())
                    .value(max.value() + "")
                    .build();
            return Optional.of(message);
        }
        throw new IllegalArgumentException();
    }

}
