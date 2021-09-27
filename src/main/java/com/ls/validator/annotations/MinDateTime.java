package com.ls.validator.annotations;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.validators.impl.MinDateTimeValidator")
public @interface MinDateTime {

    String value();

    String message() default "${field} must be greater than or equal to ${value}";

    String language();

    boolean key() default false;

    boolean minIsNow() default true;
}
