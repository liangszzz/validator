package com.ls.validator.annotations;

import java.lang.annotation.*;

/**
 * input value must be <= setting
 * apply to any value which can parse with Double.parseDouble(value.toString())
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.validators.impl.MaxValidator")
public @interface Max {

    double value() default 0d;

    String message() default "${field} must be less than or equal to ${value}";

    String language() default "";

    boolean key() default false;
}
