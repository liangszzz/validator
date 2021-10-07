package com.ls.validator.annotations;

import java.lang.annotation.*;

/**
 * input value must be not empty (not null and not equal to "")
 * apply to String value
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.validators.impl.NotEmptyValidator")
public @interface NotEmpty {

    String message() default "${field} must be not empty";

    String language() default "";

    boolean key() default false;
}
