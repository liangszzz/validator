package com.ls.validator.validators;

import com.ls.validator.message.Message;

import java.lang.annotation.Annotation;
import java.util.Optional;

public interface IValidator {

    Optional<Message> apply(Object value, Annotation annotation);
}
