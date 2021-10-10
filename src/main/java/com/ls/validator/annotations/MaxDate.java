package com.ls.validator.annotations;

import java.lang.annotation.*;

/**
 * input value must be <= setting
 * apply to String value
 * apply to Date value
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.validators.impl.MaxDateValidator")
public @interface MaxDate {

    String value();

    String message() default "${field} must be less than ${value}";

    String language() default "";

    boolean key() default false;

    boolean now() default false;

    String format() default "yyyy-MM-dd HH:mm:ss";
}
