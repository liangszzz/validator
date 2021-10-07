package com.ls.validator.validators.impl;

import com.ls.validator.annotations.NotNull;
import com.ls.validator.message.Message;
import com.ls.validator.validators.abs.DefaultAbstractValidator;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class NotNullValidator extends DefaultAbstractValidator {
    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {
        if (annotation instanceof NotNull notNull) {
            if (value != null) {
                return Optional.empty();
            } else {
                return Optional.of(Message.builder()
                        .key(notNull.key())
                        .msg(notNull.message())
                        .language(notNull.language())
                        .build());
            }
        }
        throw new IllegalArgumentException();
    }
}
