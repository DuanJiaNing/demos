package com.duan.springbootdemo1.config.web.method;

import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.util.List;

/**
 * Created on 2018/10/26.
 *
 * @author DuanJiaNing
 */
public class HandlerMethodWrapper extends ServletInvocableHandlerMethod {

    private final HandlerMethodPostProcessorComposite handlerMethodPostProcessors;

    public HandlerMethodWrapper(HandlerMethod handlerMethod, HandlerMethodPostProcessorComposite handlerMethodPostProcessors) {
        super(handlerMethod);
        this.handlerMethodPostProcessors = handlerMethodPostProcessors;
    }

    // 该方法调用时 HandlerMethod 的参数装配完成，在此时可对参数进行验证
    // 验证通过后调用 super.doInvoke(..) 继续流程
    @Override
    protected Object doInvoke(Object... args) throws Exception {
        before(args);
        Object result = super.doInvoke(args);
        after(result, args);
        return result;
    }

    private void after(Object result, Object... args) {
        List<HandlerMethodPostProcessor> processors = this.handlerMethodPostProcessors.getHandlerMethodPostProcessors();
        if (!CollectionUtils.isEmpty(processors)) {
            for (HandlerMethodPostProcessor processor : processors) {
                processor.postProcessorAfterInvoke(result, this, args);
            }
        }
    }

    private void before(Object... args) {
        List<HandlerMethodPostProcessor> processors = this.handlerMethodPostProcessors.getHandlerMethodPostProcessors();
        if (!CollectionUtils.isEmpty(processors)) {
            for (HandlerMethodPostProcessor processor : processors) {
                processor.postProcessorBeforeInvoke(this, args);
            }
        }
    }

}
