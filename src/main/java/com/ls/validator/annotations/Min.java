package com.ls.validator.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.validators.impl.MinValidator")
public @interface Min {

    double value() default 0d;

    String message() default "${field} must be greater than or equal to ${value}";

    String language() default "";

    boolean key() default false;
}
