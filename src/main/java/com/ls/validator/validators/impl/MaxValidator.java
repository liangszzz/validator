package com.ls.validator.validators.impl;

import com.ls.validator.annotations.Max;
import com.ls.validator.message.Message;
import com.ls.validator.validators.IValidator;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Optional;

@Slf4j
public class MaxValidator implements IValidator {

    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {
        if (value != null && annotation instanceof Max max) {
            double v;
            try {
                v = Double.parseDouble(value.toString());
                if (v <= max.value()) {
                    return Optional.empty();
                }
            } catch (NumberFormatException e) {
                if (log.isDebugEnabled()) {
                    log.debug("parseDouble exception", e);
                }
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
