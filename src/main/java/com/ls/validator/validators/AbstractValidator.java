package com.ls.validator.validators;

import java.time.format.DateTimeFormatter;

public abstract class AbstractValidator implements IValidator {

    protected AbstractValidator() {
    }

    protected static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    protected static final DateTimeFormatter localDateDf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter localTimeDf = DateTimeFormatter.ofPattern("HH:mm:ss");
    protected static final DateTimeFormatter localDateTimeDf = DateTimeFormatter.ofPattern(FORMAT);

}
