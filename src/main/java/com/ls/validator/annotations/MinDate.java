package com.ls.validator.annotations;

import java.lang.annotation.*;

/**
 * input value must be >= setting
 * apply to any value which can parse with Double.parseDouble(value.toString())
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.validators.impl.MinDateValidator")
public @interface MinDate {

    String value();

    String message() default "${field} must be greater than or equal to ${value}";

    String language();

    boolean key() default false;

    boolean now() default false;

    String format() default "yyyy-MM-dd HH:mm:ss";
}
