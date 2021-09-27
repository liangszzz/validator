package com.ls.validator.validators;

import com.ls.validator.message.Message;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;
import java.util.Optional;

@NoArgsConstructor
public class AbstractValidatorImpl extends AbstractValidator {

    @Override
    public Optional<Message> apply(Object value, Annotation annotation) {
        return Optional.empty();
    }
    
}
