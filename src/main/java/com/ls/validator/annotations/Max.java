package com.ls.validator.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.validators.impl.MaxValidator")
public @interface Max {

    double value() default 0d;

    String message() default "${field} must be less than or equal to ${value}";

    String language();

    boolean key() default false;
}
