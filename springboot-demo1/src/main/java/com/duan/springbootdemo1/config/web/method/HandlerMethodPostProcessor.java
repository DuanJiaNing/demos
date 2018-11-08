package com.duan.springbootdemo1.config.web.method;

import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

/**
 * Created on 2018/11/7.
 *
 * @author DuanJiaNing
 */
public interface HandlerMethodPostProcessor {

    /**
     * controller 层方法参数装配完成，方法执行前调用
     *
     * @param handlerMethod HandlerMethod
     * @param args          controller 方法参数
     */
    default void postProcessorBeforeInvoke(ServletInvocableHandlerMethod handlerMethod, Object... args) {
    }

    /**
     * controller 方法执行完成，结果输出前调用（HandlerMethodReturnValueHandler#handleReturnValue 调用之前）
     *
     * @param handlerMethod HandlerMethod
     * @param args          controller 方法参数
     * @param result        方法执行结果
     */
    default void postProcessorAfterInvoke(Object result, ServletInvocableHandlerMethod handlerMethod, Object... args) {
    }

}
