package com.ls.validator;

import com.ls.validator.annotations.Validate;
import com.ls.validator.utils.MethodUtils;
import com.ls.validator.validators.Validators;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Validator {

    private static final ConcurrentHashMap<String, Method> validatorsHashMap = new ConcurrentHashMap<>();

    static {
        try {
            validatorsHashMap.put("com.ls.validator.utils.Validators.minValidate",
                    Validators.class.getMethod("minValidate", Object.class, Annotation.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public static List<String> validator(Object obj) {
        List<String> errStr = new ArrayList<>();
        Class<?> objClass = obj.getClass();
        Field[] declaredFields = objClass.getDeclaredFields();
        try {
            for (Field declaredField : declaredFields) {
                String getMethodName = MethodUtils.getGetMethodName(declaredField);
                Method getMethod = objClass.getDeclaredMethod(getMethodName);

                Annotation[] annotations = declaredField.getAnnotations();

                for (Annotation annotation : annotations) {
                    Validate validate = annotation.annotationType().getAnnotation(Validate.class);
                    Method validateMethod = getValidateMethod(validate);
                    Object fieldValue = MethodUtils.getFieldValue(obj, getMethod);
                    Object invoke = validateMethod.invoke(null, fieldValue, annotation);
                    if (invoke instanceof Optional opt && opt.isPresent()) {
                        Object o = opt.get();
                        errStr.add(o.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            errStr.add("exception:" + e.getCause().getMessage());
        }
        return errStr;
    }

    /**
     * add new validator to map
     *
     * @param key             class name +.+ method name  (com.ls.validator.utils.Validators.minValidate)
     * @param validatorMethod validator method
     */
    public static void addValidatorMap(String key, Method validatorMethod) {
        validatorsHashMap.putIfAbsent(key, validatorMethod);
    }

    /**
     * get validator by validate annotation
     *
     * @param validate validate annation
     * @return validate method
     * @throws ClassNotFoundException e
     * @throws NoSuchMethodException  e
     */
    private static Method getValidateMethod(Validate validate) throws ClassNotFoundException, NoSuchMethodException {
        String clazz = validate.clazz();
        String method = validate.method();
        Method targetMethod = validatorsHashMap.get(clazz + method);
        if (targetMethod != null) return targetMethod;

        Class<?> targetClazz = Class.forName(clazz);
        targetMethod = targetClazz.getDeclaredMethod(method, Object.class, Annotation.class);

        return targetMethod;
    }
}
