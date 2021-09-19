package com.ls.validator.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodUtils {

    /**
     * get setter method name
     *
     * @param field field
     * @return methodName
     */
    public static String getSetMethodName(Field field) {
        assert field != null;
        String name = field.getName();
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * get getter method
     *
     * @param field field
     * @return methodName
     */
    public static String getGetMethodName(Field field) {
        assert field != null;
        String name = field.getName();
        return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * invoke method and return method value
     *
     * @param obj    obj
     * @param method method
     * @return method value
     * @throws InvocationTargetException e
     * @throws IllegalAccessException    e
     */
    public static Object getFieldValue(Object obj, Method method) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        return method.invoke(obj);
    }
}
