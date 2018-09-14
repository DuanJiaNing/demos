package com.duan.springbootdemo.verify.interceptor;

import com.duan.springbootdemo.verify.VerifyRule;
import com.duan.springbootdemo.verify.VerifyValueRule;
import com.duan.springbootdemo.verify.annoation.*;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created on 2018/9/14.
 *
 * @author DuanJiaNing
 */
public class RequestParamVerifyInterceptor extends HandlerInterceptorAdapter {

    /**
     * 是否支持预处理方式，包含 RequestParamsVerify 注解才会拦截
     */
    private boolean canHandle(Object handler) {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 方法注解
        if (method.isAnnotationPresent(RequestParamsValueVerify.class) ||
                method.isAnnotationPresent(RequestParamsVerify.class) ||
                method.isAnnotationPresent(RequestParamValueVerify.class) ||
                method.isAnnotationPresent(RequestParamVerify.class)) {
            return true;
        }

        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        if (methodParameters == null || methodParameters.length == 0) {
            return false;
        }

        // 参数注解
        for (MethodParameter methodParameter : methodParameters) {
            if (methodParameter.hasParameterAnnotation(ParamValueVerify.class) ||
                    methodParameter.hasParameterAnnotation(ParamVerify.class)) {
                return true;
            }
        }

        return false;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

        if (!(handle instanceof HandlerMethod) || !canHandle(handle)) {
            return true;
        }

        // 校验基础参数
        if (!commonParamIntercept(request, response, handle)) {
            verifyFail(request, response, handle);
            return false;
        }

        // 校验参数
        if (!handleParamVerify(request, response, (HandlerMethod) handle)) {
            verifyFail(request, response, handle);
            return false;
        }

        return true;
    }

    protected void verifyFail(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

    }

    /**
     * 基础参数拦截
     */
    protected boolean commonParamIntercept(HttpServletRequest request, HttpServletResponse response, Object handle) {
        return true;
    }

    /**
     * 其它参数校验
     */
    private boolean handleParamVerify(HttpServletRequest request, HttpServletResponse response, HandlerMethod handle) {

        // 参数校验注解
        MethodParameter[] parameters = handle.getMethodParameters();
        if (parameters == null || parameters.length == 0) {
            return true;
        }

        for (MethodParameter parameter : parameters) {
            ParamVerify annotation = parameter.getMethodAnnotation(ParamVerify.class);
            if (annotation != null && !verifyParam(annotation.rule(), getParamValue(request, handle, parameter))) {
                verifyFail(request, response, handle, parameter);
                return false;
            }

            ParamValueVerify valueVerify = parameter.getMethodAnnotation(ParamValueVerify.class);
            if (valueVerify != null && !verifyParam(valueVerify.valueMapping().rule(),
                    getParamValue(request, handle, parameter), valueVerify.valueMapping().value())) {
                verifyFail(request, response, handle, parameter);
                return false;
            }
        }

        // 方法校验注解
        Method method = handle.getMethod();
        RequestParamVerify paramVerify = method.getAnnotation(RequestParamVerify.class);
        if (paramVerify != null && !verifyParam(paramVerify.rule(), getParamValue(request, handle, paramVerify.param()))) {
            return false;
        }

        RequestParamValueVerify paramValueVerify = method.getAnnotation(RequestParamValueVerify.class);
        if (paramValueVerify != null && !verifyParam(paramValueVerify.rule(),
                getParamValue(request, handle, paramValueVerify.param()), paramValueVerify.value())) {
            return false;
        }

        RequestParamsVerify paramsVerify = method.getAnnotation(RequestParamsVerify.class);
        if (paramsVerify != null) {
            RequestParamVerify[] verifies = paramsVerify.value();
            if (verifies.length != 0) {
                for (RequestParamVerify verify : verifies) {
                    if (!verifyParam(verify.rule(), getParamValue(request, handle, verify.param()))) {
                        return false;
                    }
                }
            }
        }

        RequestParamsValueVerify paramsValueVerify = method.getAnnotation(RequestParamsValueVerify.class);
        if (paramsValueVerify != null) {
            RequestParamValueVerify[] verifies = paramsValueVerify.valueMapping();
            if (verifies.length != 0) {
                for (RequestParamValueVerify verify : verifies) {
                    if (!verifyParam(verify.rule(), getParamValue(request, handle, verify.param()), verify.value())) {
                        return false;
                    }
                }
            }

        }

        return true;
    }

    private String getParamValue(HttpServletRequest request, HandlerMethod handle, MethodParameter parameter) {
        return request.getParameter(parameter.getParameterName());
    }

    private String getParamValue(HttpServletRequest request, HandlerMethod handle, String parame) {
        return request.getParameter(parame);
    }

    private boolean verifyParam(VerifyValueRule rule, String paramValue, String desValue) {
        return false;
    }

    private boolean verifyParam(VerifyRule rule, String paramValue) {
        return false;
    }

    // 校验出错
    private void verifyFail(HttpServletRequest request, HttpServletResponse response, HandlerMethod handle,
                            MethodParameter parameter) {
    }

}
