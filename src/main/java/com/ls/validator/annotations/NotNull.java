package com.ls.validator.annotations;

import java.lang.annotation.*;

/**
 * input value must be not null
 * apply to any object
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.validators.impl.NotNullValidator")
public @interface NotNull {

    String message() default "${field} must be not null";

    String language() default "";

    boolean key() default false;
}
