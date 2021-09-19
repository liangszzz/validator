package com.ls.validator.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.utils.Validators", method = "maxValidate")
public @interface Max {

    double value() default 0d;

    String message() default "${field} must be less than or equal to ${value}";

    String language() default "en";
}
