package com.ls.validator.validators.impl;

import com.ls.validator.annotations.NotBlank;
import com.ls.validator.message.Message;
import com.ls.validator.validators.abs.DefaultAbstractValidator;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Optional;

@Slf4j
public class NotBlankValidator extends DefaultAbstractValidator {

    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {

        if (value instanceof String v && annotation instanceof NotBlank notBlank) {

            if (!"".equals(v.trim())) {
                return Optional.empty();
            } else {
                return Optional.of(Message.builder()
                        .key(notBlank.key())
                        .language(notBlank.language())
                        .msg(notBlank.message())
                        .build());
            }

        }
        throw new IllegalArgumentException();
    }
}
