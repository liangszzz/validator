package com.ls.validator.validators;

import com.ls.validator.annotations.Max;
import com.ls.validator.annotations.Min;

import java.lang.annotation.Annotation;
import java.util.Optional;


public class Validators {


    /**
     * min value validate
     *
     * @param value      check value
     * @param annotation min annotation
     * @return optional message
     */
    public static Optional<String> minValidate(Object value, Annotation annotation) throws NoSuchMethodException, NumberFormatException {
        if (value != null && annotation instanceof Min min) {
            double v = Double.parseDouble(value.toString());
            if (v > min.value()) {
                return Optional.empty();
            }
            return Optional.of(min.message());
        }
        throw new NoSuchMethodException();
    }

    /**
     * min value validate
     *
     * @param value      check value
     * @param annotation min annotation
     * @return optional message
     */
    public static Optional<String> maxValidate(Object value, Annotation annotation) throws NoSuchMethodException, NumberFormatException {
        if (value != null && annotation instanceof Max max) {
            double v = Double.parseDouble(value.toString());
            if (v <= max.value()) {
                return Optional.empty();
            }
            return Optional.of(max.message());
        }
        throw new NoSuchMethodException();
    }
}
