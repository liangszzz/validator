package com.ls.validator.validators.impl;

import com.ls.validator.annotations.NotEmpty;
import com.ls.validator.message.Message;
import com.ls.validator.validators.abs.DefaultAbstractValidator;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Optional;

@Slf4j
public class NotEmptyValidator extends DefaultAbstractValidator {
    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {

        if (value instanceof String v && annotation instanceof NotEmpty notEmpty) {
            if (!"".equals(v)) {
                return Optional.empty();
            } else {
                return Optional.of(Message.builder()
                        .key(notEmpty.key())
                        .language(notEmpty.language())
                        .msg(notEmpty.message())
                        .build());
            }
        }
        throw new IllegalArgumentException();
    }
}
