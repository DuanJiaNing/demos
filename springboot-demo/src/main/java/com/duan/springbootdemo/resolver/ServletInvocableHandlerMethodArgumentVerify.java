package com.duan.springbootdemo.resolver;

import com.duan.springbootdemo.verify.VerifyRule;
import com.duan.springbootdemo.verify.VerifyValueRule;
import com.duan.springbootdemo.verify.annoation.*;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created on 2018/9/17.
 *
 * @author DuanJiaNing
 */
public class ServletInvocableHandlerMethodArgumentVerify extends ServletInvocableHandlerMethod {

    public ServletInvocableHandlerMethodArgumentVerify(HandlerMethod handlerMethod) {
        super(handlerMethod);
    }

    // 该方法调用时 HandlerMethod 的参数装配完成，在此时可对参数进行验证
    // 验证通过后调用 super.doInvoke(..) 继续流程
    @Override
    protected Object doInvoke(Object... args) throws Exception {
        HandlerMethod handlerMethod = getResolvedFromHandlerMethod();

        // 校验基础参数
        if (!commonParamIntercept(handlerMethod, args)) {
            // 参数校验未通过
            return false;
        }

        // 校验参数
        if (!handleParamVerify(handlerMethod, args)) {
            // 参数校验未通过
            return false;
        }

        return super.doInvoke(args);
    }

    // args 顺序对应 HandlerMethod.getMethodParameters() 顺序
    private boolean handleParamVerify(HandlerMethod handlerMethod, Object... args) {

        // 1. 参数校验注解
        MethodParameter[] parameters = handlerMethod.getMethodParameters();
        if (parameters == null || parameters.length == 0) {
            return true;
        }

        for (int i = 0; i < parameters.length; i++) {
            MethodParameter parameter = parameters[i];
            Object value = args[i];

            // ParamVerify
            ParamVerify annotation = parameter.getParameterAnnotation(ParamVerify.class);
            if (annotation != null && !verifyParam(annotation.rule(), value)) {
                return false;
            }

            // ParamValueVerify
            ParamValueVerify valueVerify = parameter.getParameterAnnotation(ParamValueVerify.class);
            if (valueVerify != null && !verifyParamValue(valueVerify.valueMapping().rule(), value,
                    valueVerify.valueMapping().value())) {
                return false;
            }
        }

        // 2. 方法校验注解
        Method method = handlerMethod.getMethod();

        // RequestParamVerify
        RequestParamVerify paramVerify = method.getAnnotation(RequestParamVerify.class);
        if (paramVerify != null && !verifyParam(paramVerify.rule(), getParamValue(handlerMethod, paramVerify.param(), args))) {
            return false;
        }

        // RequestParamValueVerify
        RequestParamValueVerify paramValueVerify = method.getAnnotation(RequestParamValueVerify.class);
        if (paramValueVerify != null && !verifyParamValue(paramValueVerify.rule(),
                getParamValue(handlerMethod, paramValueVerify.param(), args), paramValueVerify.value())) {
            return false;
        }

        // RequestParamsVerify
        RequestParamsVerify paramsVerify = method.getAnnotation(RequestParamsVerify.class);
        if (paramsVerify != null) {
            RequestParamVerify[] verifies = paramsVerify.value();
            if (verifies.length != 0) {
                for (RequestParamVerify verify : verifies) {
                    if (!verifyParam(verify.rule(), getParamValue(handlerMethod, verify.param()))) {
                        return false;
                    }
                }
            }
        }

        // RequestParamsValueVerify
        RequestParamsValueVerify paramsValueVerify = method.getAnnotation(RequestParamsValueVerify.class);
        if (paramsValueVerify != null) {
            RequestParamValueVerify[] verifies = paramsValueVerify.valueMapping();
            if (verifies.length != 0) {
                for (RequestParamValueVerify verify : verifies) {
                    if (!verifyParamValue(verify.rule(), getParamValue(handlerMethod, verify.param(), args), verify.value())) {
                        return false;
                    }
                }
            }

        }

        return true;
    }

    private boolean commonParamIntercept(HandlerMethod handlerMethod, Object... args) {
        return true;
    }

    /**
     * 基础参数拦截
     */
    protected boolean commonParamIntercept(HttpServletRequest request, HttpServletResponse response, Object handle) {
        return true;
    }

    private Object getParamValue(HandlerMethod handle, String parameterName, Object... args) {

        MethodParameter[] parameters = handle.getMethodParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getParameterName().equals(parameterName)) {
                return args[i];
            }
        }

        return null;
    }

    private boolean verifyParamValue(VerifyValueRule rule, Object paramValue, Object verifyValue) {

        switch (rule) {
            case NON:
                break;
            case VALUE_EQUAL:
                break;
            case VALUE_GREATER_THAN:
                break;
            case VALUE_NOT_GREATER_THAN:
                break;
            case VALUE_LESS_THAN:
                break;
            case VALUE_NOT_LESS_THAN:
                break;
            case TEXT_REGEX:
                break;
            case TEXT_LENGTH_EQUAL:
                break;
            case TEXT_LENGTH_GREATER_THAN:
                break;
            case TEXT_LENGTH_NOT_GREATER_THAN:
                break;
            case TEXT_LENGTH_LESS_THAN:
                break;
            case TEXT_LENGTH_NOT_LESS_THAN:
                break;
            case COLLECTION_SIZE_GREATER_THAN:
                break;
            case COLLECTION_SIZE_NOT_GREATER_THAN:
                break;
            case COLLECTION_SIZE_LESS_THAN:
                break;
            case COLLECTION_SIZE_NOT_LESS_THAN:
                break;
            case COLLECTION_SIZE_EQUAL:
                break;
        }

        return false;
    }

    private boolean verifyParam(VerifyRule rule, Object paramValue) {

        switch (rule) {
            case NOT_NULL: {
                return paramValue != null;
            }
            case NOT_EMPTY: {
                if (paramValue == null) {
                    return false;
                }

                // Collection
                if (paramValue instanceof Collection) {
                    Collection coll = (Collection) paramValue;
                    return coll.size() > 0;
                }

                // array
                if (paramValue.getClass().isArray()) {
                    return Array.getLength(paramValue) > 0;
                }

                return false;
            }
            case NOT_BLANK: {
                if (paramValue == null) {
                    return false;
                }

                // string
                if (paramValue instanceof CharSequence) {
                    CharSequence chaz = (CharSequence) paramValue;
                    return chaz.length() > 0;
                }

                return false;
            }
            case NON_NEGATIVE: {
                if (paramValue == null) {
                    return false;
                }

                if (paramValue instanceof Number) {
                    Number value = (Number) paramValue;
                    /*value.byteValue()
                    value.shortValue()*/
                    if (value.doubleValue() < 0) return false;
                    if (value.floatValue() < 0) return false;
                    if (value.intValue() < 0) return false;
                    if (value.longValue() < 0) return false;
                }

                return true;
            }
        }

        return false;
    }

}
