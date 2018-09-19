package com.duan.springbootdemo.verify;

import com.duan.springbootdemo.config.WebConfig;
import com.duan.springbootdemo.domain.Result;
import com.duan.springbootdemo.verify.annoation.ParamVerifyComposite;
import com.duan.springbootdemo.verify.annoation.method.*;
import com.duan.springbootdemo.verify.annoation.parameter.ParamValueVerify;
import com.duan.springbootdemo.verify.annoation.parameter.ParamVerify;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created on 2018/9/17.
 * 使用自定义注解对 controller 的 HandlerMethod 进行参数验证
 *
 * <p> 像下面这样覆盖 WebMvcRegistrationsAdapter#getRequestMappingHandlerAdapter 方法启用检验工具及相关注解:
 *
 * <pre class="code">
 * {@code @Configuration}
 * public class WebConfig extends WebMvcRegistrationsAdapter {
 *
 *     {@code @Override}
 *     public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
 *         return new RequestMappingHandlerAdapter() {
 *             {@code @Override}
 *             protected ServletInvocableHandlerMethod createInvocableHandlerMethod(HandlerMethod handlerMethod) {
 *                 return new ServletInvocableHandlerMethodArgumentVerify(handlerMethod) {
 *                     {@code @Override}
 *                     protected Object warpResultFail(ServletInvocableHandlerMethodArgumentVerify.HandleResult result) {
 *                         Object value = result.value;
 *                         MethodParameter parameter = result.parameter;
 *                         return super.warpResultFail(result);
 *                     }
 *                 };
 *             }
 *         };
 *     }
 * }</pre>
 *
 * @author DuanJiaNing
 * @see WebConfig#getRequestMappingHandlerAdapter()
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

        // 校验参数
        HandleResult result = handleParamVerify(handlerMethod, args);
        if (result != null) {
            // 参数校验未通过
            return warpResultFail(result);
        }

        return super.doInvoke(args);
    }


    protected Object warpResultFail(HandleResult result) {

        String rule = "";
        if (result.rule instanceof VerifyRule) {
            rule = ((VerifyRule) result.rule).name();
        }

        if (result.rule instanceof VerifyValueRule) {
            rule = ((VerifyValueRule) result.rule).name();
        }

        return new Result<>(rule + ": argument [" + result.parameter.getParameterName() + "] can't be ['" + result.value + "']");
    }

    // args 顺序对应 HandlerMethod.getMethodParameters() 顺序
    // 返回 null 表示校验成功，否则失败
    private HandleResult handleParamVerify(HandlerMethod handlerMethod, Object... args) {

        // 1. 参数校验注解
        MethodParameter[] parameters = handlerMethod.getMethodParameters();
        if (parameters == null || parameters.length == 0) {
            return null;
        }

        for (int i = 0; i < parameters.length; i++) {
            MethodParameter parameter = parameters[i];
            Object value = args[i]; // 已经过 spring 转换的参数

            // ParamVerify
            if (parameter.hasParameterAnnotation(ParamVerify.class)) {
                ParamVerify annotation = parameter.getParameterAnnotation(ParamVerify.class);
                if (!verifyParam(annotation.rule(), value)) {
                    return new HandleResult(parameter, value, annotation, annotation.rule());
                }
            }

            // ParamValueVerify
            if (parameter.hasParameterAnnotation(ParamValueVerify.class)) {
                ParamValueVerify annotation = parameter.getParameterAnnotation(ParamValueVerify.class);
                if (!verifyParamValue(annotation.rule(), value, annotation.value())) {
                    return new HandleResult(parameter, value, annotation, annotation.rule());
                }
            }
        }

        // 2. 方法校验注解
        Method method = handlerMethod.getMethod();

        // RequestParamVerify
        if (handlerMethod.hasMethodAnnotation(RequestParamVerify.class)) {
            RequestParamVerify annotation = method.getAnnotation(RequestParamVerify.class);
            MethodParameter parameter = getParam(handlerMethod, annotation.param());
            Object paramValue = getParamValue(handlerMethod, annotation.param(), args);
            if (parameter != null && !verifyParam(annotation.rule(), paramValue)) {
                return new HandleResult(parameter, paramValue, annotation, annotation.rule());
            }
        }

        // RequestParamValueVerify
        if (handlerMethod.hasMethodAnnotation(RequestParamValueVerify.class)) {
            RequestParamValueVerify annotation = method.getAnnotation(RequestParamValueVerify.class);
            MethodParameter parameter = getParam(handlerMethod, annotation.param());
            Object paramValue = getParamValue(handlerMethod, annotation.param(), args);
            if (parameter != null && !verifyParamValue(annotation.rule(), paramValue, annotation.value())) {
                return new HandleResult(parameter, paramValue, annotation, annotation.rule());
            }
        }

        // RequestParamsVerify
        if (handlerMethod.hasMethodAnnotation(RequestParamsVerify.class)) {
            RequestParamsVerify annotation = method.getAnnotation(RequestParamsVerify.class);
            RequestParamVerify[] verifies = annotation.value();
            if (verifies.length != 0) {
                for (RequestParamVerify verify : verifies) {
                    MethodParameter parameter = getParam(handlerMethod, verify.param());
                    Object paramValue = getParamValue(handlerMethod, verify.param(), args);
                    if (parameter != null && !verifyParam(verify.rule(), paramValue)) {
                        return new HandleResult(parameter, paramValue, annotation, verify.rule());
                    }
                }
            }
        }

        // RequestParamsValueVerify
        if (handlerMethod.hasMethodAnnotation(RequestParamsValueVerify.class)) {
            RequestParamsValueVerify annotation = method.getAnnotation(RequestParamsValueVerify.class);
            RequestParamValueVerify[] verifies = annotation.value();
            if (verifies.length != 0) {
                for (RequestParamValueVerify verify : verifies) {
                    MethodParameter parameter = getParam(handlerMethod, verify.param());
                    Object paramValue = getParamValue(handlerMethod, verify.param(), args);
                    if (parameter != null && !verifyParamValue(verify.rule(), paramValue, verify.value())) {
                        return new HandleResult(parameter, paramValue, annotation, verify.rule());
                    }
                }
            }
        }

        // RequestParamsVerifyComposite
        if (handlerMethod.hasMethodAnnotation(RequestParamsVerifyComposite.class)) {
            RequestParamsVerifyComposite annotation = method.getAnnotation(RequestParamsVerifyComposite.class);
            ParamVerifyComposite[] verifies = annotation.value();
            if (verifies.length != 0) {
                for (ParamVerifyComposite verify : verifies) {
                    RequestParamVerify value = verify.value();
                    RequestParamValueVerify valueVerify = verify.valueVerify();
                    if (value.param().equals("") && valueVerify.param().equals("")) {
                        continue;
                    }

                    if (!valueVerify.param().equals("")) { // 若两者都指定只校验 valueVerify

                        MethodParameter parameter = getParam(handlerMethod, valueVerify.param());
                        Object paramValue = getParamValue(handlerMethod, valueVerify.param(), args);
                        if (parameter != null && !verifyParamValue(valueVerify.rule(), paramValue, valueVerify.value())) {
                            return new HandleResult(parameter, paramValue, valueVerify, valueVerify.rule());
                        }
                    } else {

                        MethodParameter parameter = getParam(handlerMethod, value.param());
                        Object paramValue = getParamValue(handlerMethod, value.param(), args);
                        if (parameter != null && !verifyParam(value.rule(), paramValue)) {
                            return new HandleResult(parameter, paramValue, value, value.rule());
                        }
                    }

                }
            }
        }

        return null;
    }

    private MethodParameter getParam(HandlerMethod handlerMethod, String parameterName) {

        MethodParameter[] parameters = handlerMethod.getMethodParameters();
        for (int i = 0; i < parameters.length; i++) {
            MethodParameter parameter = parameters[i];
            if (parameter.getParameterName().equals(parameterName)) {
                return parameter;
            }
        }

        return null;
    }

    protected static class HandleResult {
        public MethodParameter parameter;
        public Object value;
        public Object annotation;
        public Object rule;

        public HandleResult(MethodParameter parameter, Object value, Object annotation, Object rule) {
            this.parameter = parameter;
            this.value = value;
            this.annotation = annotation;
            this.rule = rule;
        }
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

    @SuppressWarnings("unchecked")
    private boolean verifyParamValue(VerifyValueRule rule, Object paramValue, String verifyValue) {
        if (paramValue == null)
            return false;

        switch (rule) {
            case EQUAL: {
                // 依赖于对象的 toString 方法
                return verifyValue.equals(paramValue.toString());
            }
            case OBJECT_EQUAL:
                return paramValue == verifyValue;

            // 依赖于 Comparable 接口
            case VALUE_GREATER_THAN:
                if (paramValue instanceof Number) {
                    return ((Number) paramValue).doubleValue() > Double.valueOf(verifyValue);
                }
                break;
            case VALUE_NOT_GREATER_THAN:
                if (paramValue instanceof Number) {
                    return ((Number) paramValue).doubleValue() <= Double.valueOf(verifyValue);
                }
                break;
            case VALUE_LESS_THAN:
                if (paramValue instanceof Number) {
                    return ((Number) paramValue).doubleValue() < Double.valueOf(verifyValue);
                }
                break;
            case VALUE_NOT_LESS_THAN:
                if (paramValue instanceof Number) {
                    return ((Number) paramValue).doubleValue() >= Double.valueOf(verifyValue);
                }
                break;
            case TEXT_REGEX:
                if (paramValue instanceof CharSequence) {
                    return String.valueOf(paramValue).matches(verifyValue);
                }
                break;
            case TEXT_LENGTH_EQUAL:
                if (paramValue instanceof CharSequence) {
                    return ((CharSequence) paramValue).length() == Integer.valueOf(verifyValue);
                }
                break;
            case TEXT_LENGTH_GREATER_THAN:
                if (paramValue instanceof CharSequence) {
                    return ((CharSequence) paramValue).length() > Integer.valueOf(verifyValue);
                }
                break;
            case TEXT_LENGTH_NOT_GREATER_THAN:
                if (paramValue instanceof CharSequence) {
                    return ((CharSequence) paramValue).length() <= Integer.valueOf(verifyValue);
                }
                break;
            case TEXT_LENGTH_LESS_THAN:
                if (paramValue instanceof CharSequence) {
                    return ((CharSequence) paramValue).length() < Integer.valueOf(verifyValue);
                }
                break;
            case TEXT_LENGTH_NOT_LESS_THAN:
                if (paramValue instanceof CharSequence) {
                    return ((CharSequence) paramValue).length() >= Integer.valueOf(verifyValue);
                }
                break;
            case COLLECTION_SIZE_GREATER_THAN: {
                Integer vLen = Integer.valueOf(verifyValue);
                // Collection
                if (paramValue instanceof Collection) {
                    Collection coll = (Collection) paramValue;
                    return vLen.compareTo(coll.size()) < 0;
                }

                // array
                if (paramValue.getClass().isArray()) {
                    return vLen.compareTo(Array.getLength(paramValue)) < 0;
                }
                break;
            }
            case COLLECTION_SIZE_NOT_GREATER_THAN: {
                Integer vLen = Integer.valueOf(verifyValue);
                // Collection
                if (paramValue instanceof Collection) {
                    Collection coll = (Collection) paramValue;
                    return vLen.compareTo(coll.size()) >= 0;
                }

                // array
                if (paramValue.getClass().isArray()) {
                    return vLen.compareTo(Array.getLength(paramValue)) >= 0;
                }
                break;
            }
            case COLLECTION_SIZE_LESS_THAN: {
                Integer vLen = Integer.valueOf(verifyValue);
                // Collection
                if (paramValue instanceof Collection) {
                    Collection coll = (Collection) paramValue;
                    return vLen.compareTo(coll.size()) > 0;
                }

                // array
                if (paramValue.getClass().isArray()) {
                    return vLen.compareTo(Array.getLength(paramValue)) > 0;
                }
                break;
            }
            case COLLECTION_SIZE_NOT_LESS_THAN: {
                Integer vLen = Integer.valueOf(verifyValue);
                // Collection
                if (paramValue instanceof Collection) {
                    Collection coll = (Collection) paramValue;
                    return vLen.compareTo(coll.size()) <= 0;
                }

                // array
                if (paramValue.getClass().isArray()) {
                    return vLen.compareTo(Array.getLength(paramValue)) <= 0;
                }
                break;
            }
            case COLLECTION_SIZE_EQUAL: {
                Integer vLen = Integer.valueOf(verifyValue);
                // Collection
                if (paramValue instanceof Collection) {
                    Collection coll = (Collection) paramValue;
                    return vLen.compareTo(coll.size()) == 0;
                }

                // array
                if (paramValue.getClass().isArray()) {
                    return vLen.compareTo(Array.getLength(paramValue)) == 0;
                }
                break;
            }
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
