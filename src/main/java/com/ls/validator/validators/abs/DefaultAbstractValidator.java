package com.ls.validator.validators.abs;

import com.ls.validator.validators.IValidator;

import java.util.HashSet;
import java.util.Set;

public abstract class DefaultAbstractValidator implements IValidator {

    @Override
    public Set<Class<?>> supportedTypes() {
        Set<Class<?>> set = new HashSet<>();
        set.add(String.class);
        return set;
    }
}
