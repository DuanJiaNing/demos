package com.duan.springbootdemo1.config.handler;

import com.duan.springbootdemo1.config.annotation.OperatorLog;
import com.duan.springbootdemo1.config.web.method.HandlerMethodPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2018/11/7.
 *
 * @author DuanJiaNing
 */
@Component
public class OperatorLogHandler implements HandlerMethodPostProcessor {

    @Override
    public void postProcessorAfterInvoke(Object result, ServletInvocableHandlerMethod handlerMethod, Object... args) {
        OperatorLog annotation = handlerMethod.getMethodAnnotation(OperatorLog.class);
        if (annotation == null) {
            return;
        }

        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        Map<String, Object> map = getParamNameValueMap(methodParameters, args);
        LogExpressionParser logExpressionParser = new LogExpressionParser(map, result, handlerMethod, annotation.value());
        String content = logExpressionParser.parse();
        System.out.println(content);

        // TODO 日志处理

    }

    private Map<String, Object> getParamNameValueMap(MethodParameter[] methodParameters, Object[] args) {
        Map<String, Object> map = new HashMap<>(args.length);
        for (int i = 0; i < args.length; i++) {
            MethodParameter parameter = methodParameters[i];
            String name = parameter.getParameterName();
            map.put(name, args[i]);
        }

        return map;
    }

    public class LogExpressionParser {

        final Map<String, Object> paramNameValueMap;
        final Object returnValue;
        final HandlerMethod method;
        final String expression;
        final Pattern pattern = Pattern.compile("\\{(\\w|\\$).*?\\}");

        // 找到最前面的参数名
        // name.length.cc
        final Function<String, String> findFirstParamName = str -> {
            if (StringUtils.isBlank(str)) {
                return null;
            }
            int endIndex = !str.contains(".") ? str.length() : str.indexOf(".");
            return str.substring(0, endIndex);
        };

        // 截断第一个参数名
        // name.length.cc
        final Function<String, String> splitFirstParamName = str -> {
            if (StringUtils.isBlank(str) || !str.contains(".")) {
                return null;
            }
            return str.substring(str.indexOf(".") + 1);
        };

        // 调用 getter 方法
        final BiFunction<Object, String, Object> invokeGetter = (obj, fieldName) -> {

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

        LogExpressionParser(Map<String, Object> paramNameValueMap, Object returnValue, HandlerMethod method, String expression) {
            this.paramNameValueMap = paramNameValueMap;
            this.returnValue = returnValue;
            this.method = method;
            this.expression = expression;
        }

        /**
         * {parName}: {} 内部的内容将被解析，普通单词将识别为参数名
         * {$}: $ 符将被认为返回值
         * {parName.count}: .count 的形式将调用参数 parName 的 getCount 获得其值
         * {parName.role.name}: .role.name 的形式将调用参数 parName 的 getRole，再继续调用 Role 的 getName
         * {$.name}: 调用返回值的相应 getter 方法
         * <p>
         * TODO 数组或集合的处理
         *
         * @return 解析所得的内容
         */
        String parse() {
            Matcher matcher = pattern.matcher(expression);
            Map<String, String> replaceMap = new HashMap<>(matcher.groupCount());

            while (matcher.find()) {
                String str = matcher.group();
                replaceMap.put(str, parseValue(str));
            }

            if (replaceMap.size() == 0) {
                return expression;
            }

            String repl = expression;
            for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
                repl = repl.replace(entry.getKey(), entry.getValue());
            }
            return repl;

        }

        // {obj.name} {$} {$.cc} {a.b.c}
        private String parseValue(String str) {
            // obj.name $ $.cc
            String exp = str.substring(1, str.length() - 1);

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
        private String getValue(Object obj, String paramName, String exp) {
            if (paramName == null && exp != null) {
                return getValue(obj, findFirstParamName.apply(exp), splitFirstParamName.apply(exp));
            }

            if (paramName != null && exp == null) {
                Object subObj = invokeGetter.apply(obj, paramName);
                return String.valueOf(subObj);
            }

            if (paramName != null) {
                Object subObj = invokeGetter.apply(obj, paramName);
                return getValue(subObj, findFirstParamName.apply(exp), splitFirstParamName.apply(exp));
            }

            return String.valueOf(obj);
        }
    }
}
