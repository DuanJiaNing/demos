package com.duan.springbootdemo1.config.handler.log;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created on 2018/12/10.
 *
 * @author DuanJiaNing
 */
public class OperatorLogDataParser {

    private final Map<String, Object> paramNameValueMap;
    private final Object returnValue;

    // 找到最前面的参数名
    // name.length.cc
    private final Function<String, String> findFirstParamName = str -> {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        int endIndex = !str.contains(".") ? str.length() : str.indexOf(".");
        return str.substring(0, endIndex);
    };

    // 截断第一个参数名
    // name.length.cc
    private final Function<String, String> splitFirstParamName = str -> {
        if (StringUtils.isBlank(str) || !str.contains(".")) {
            return null;
        }
        return str.substring(str.indexOf(".") + 1);
    };

    // 调用 getter 方法
    private final BiFunction<Object, String, Object> invokeGetter = (obj, fieldName) -> {

        Class<?> clazz = obj.getClass();
        String name = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        try {
            Method method = clazz.getMethod(name);
            return method.invoke(obj, null);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    };

    public OperatorLogDataParser(Map<String, Object> paramNameValueMap, Object returnValue) {
        this.paramNameValueMap = paramNameValueMap;
        this.returnValue = returnValue;
    }

    // obj.name $ $.cc a.b.c
    public Object parseValue(String exp) {

        if (exp.startsWith("$")) { // $.* 返回值
            if (!exp.contains(".")) { // $
                return getValue(returnValue, null, null);
            }

            // cc
            String tm = exp.substring(2);
            int endIndex = !tm.contains(".") ? tm.length() : tm.indexOf(".");
            return getValue(returnValue, findFirstParamName.apply(tm.substring(0, endIndex)),
                    endIndex == tm.length() ? null : tm.substring(endIndex + 1));
        } else { // obj.* 入参
            if (exp.contains(".")) { // obj.name
                String firstParamName = findFirstParamName.apply(exp);
                return getValue(paramNameValueMap.get(firstParamName), null, splitFirstParamName.apply(exp));
            }

            // obj
            return getValue(paramNameValueMap.get(exp), null, null);
        }
    }

    /**
     * @param obj       当前对象
     * @param paramName 当前对象成员变量名
     * @param exp       未处理的子变量
     */
    private Object getValue(Object obj, String paramName, String exp) {
        if (paramName == null && exp != null) {
            return getValue(obj, findFirstParamName.apply(exp), splitFirstParamName.apply(exp));
        }

        if (paramName != null && exp == null) {
            return invokeGetter.apply(obj, paramName);
        }

        if (paramName != null) {
            Object subObj = invokeGetter.apply(obj, paramName);
            return getValue(subObj, findFirstParamName.apply(exp), splitFirstParamName.apply(exp));
        }

        return obj;
    }
}
