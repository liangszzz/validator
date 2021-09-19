package com.ls.validator.ivalidators;

import java.lang.annotation.Annotation;
import java.util.Optional;

public interface IValidator {

    Optional<String> apply(Object value, Annotation annotation) throws Exception;
}
