package com.ls.validator.annotations;

import java.lang.annotation.*;

/**
 * input value must be not blank (not null and String.trim() is not equal to "")
 * apply to String value
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Validate(clazz = "com.ls.validator.validators.impl.NotBlankValidator")
public @interface NotBlank {

    String message() default "${field} must be not blank";

    String language() default "";

    boolean key() default false;
}
