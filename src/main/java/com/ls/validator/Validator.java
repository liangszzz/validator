package com.ls.validator;

import com.ls.validator.annotations.Validate;
import com.ls.validator.message.Message;
import com.ls.validator.message.Messages;
import com.ls.validator.validators.IValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class Validator {

    @Getter
    @Setter
    private boolean fastMode;

    @Getter
    @Setter
    private Messages messages;

    public Validator(boolean fastMode, Messages messages) {
        this.fastMode = fastMode;
        this.messages = messages;
    }

    /**
     * key class path
     * value class obj
     */
    private final ConcurrentHashMap<String, IValidator> validatorsHashMap = new ConcurrentHashMap<>();

    public Set<String> validate(Object obj) {
        List<Message> errorList = new ArrayList<>();
        Class<?> objClass = obj.getClass();
        Field[] declaredFields = objClass.getDeclaredFields();

        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);
                Object value = field.get(obj);

                for (Annotation annotation : field.getAnnotations()) {
                    Validate validate = annotation.annotationType().getAnnotation(Validate.class);
                    String clazz = validate.clazz();
                    IValidator validator = getValidator(clazz);
                    if (validator != null) {
                        Optional<Message> opt = validator.apply(value, annotation);
                        if (opt.isPresent()) {
                            Message message = opt.get();
                            message.setField(field.getName());
                            errorList.add(message);
                            if (fastMode) {
                                break;
                            }
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            log.debug("validate", e);
        }
        return parseMessage(errorList);
    }

    public void addValidatorMap(String key, IValidator validator) {
        validatorsHashMap.putIfAbsent(key, validator);
    }

    private IValidator getValidator(String clazz) {
        IValidator iValidator = validatorsHashMap.get(clazz);
        if (iValidator == null) {
            initValidator(clazz);
            iValidator = validatorsHashMap.get(clazz);
        }
        return iValidator;
    }

    private void initValidator(String clazz) {
        try {
            Class<?> aClass = Class.forName(clazz);
            Constructor<?> constructor = aClass.getConstructor();
            Object instance = constructor.newInstance();
            if (instance instanceof IValidator validator) {
                addValidatorMap(clazz, validator);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.debug("initValidator", e);
        }
    }

    private Set<String> parseMessage(List<Message> message) {
        Set<String> set = new HashSet<>();
        message.forEach(msg -> {
            if (msg.isKey()) {
                set.add(messages.getMessage(msg.getMsg(), msg.getLanguage()));
            } else {
                set.add(msg.parseMsg());
            }
        });
        return set;
    }
}
