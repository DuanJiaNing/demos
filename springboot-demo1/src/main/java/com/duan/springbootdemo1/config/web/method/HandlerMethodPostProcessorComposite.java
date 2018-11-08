package com.duan.springbootdemo1.config.web.method;

import java.util.List;

/**
 * Created on 2018/11/7.
 *
 * @author DuanJiaNing
 */
public class HandlerMethodPostProcessorComposite {

    private final List<HandlerMethodPostProcessor> handlerMethodPostProcessors;

    public HandlerMethodPostProcessorComposite(List<HandlerMethodPostProcessor> handlerMethodPostProcessors) {
        this.handlerMethodPostProcessors = handlerMethodPostProcessors;
    }

    public List<HandlerMethodPostProcessor> getHandlerMethodPostProcessors() {
        return handlerMethodPostProcessors;
    }
}
