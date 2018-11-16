package com.duan.springbootdemo1.config.handler;

import com.duan.springbootdemo1.config.annotation.OperatorLog;
import com.duan.springbootdemo1.config.handler.log.LogExpressionParser;
import com.duan.springbootdemo1.config.handler.log.ControllerLogExpressionParser;
import com.duan.springbootdemo1.config.web.method.HandlerMethodPostProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/11/7.
 *
 * @author DuanJiaNing
 */
@Component
public class LogHandler implements HandlerMethodPostProcessor {

    @Override
    public void postProcessorAfterInvoke(Object result, ServletInvocableHandlerMethod handlerMethod, Object... args) {
        OperatorLog annotation = handlerMethod.getMethodAnnotation(OperatorLog.class);
        if (annotation == null) {
            return;
        } else {

            String expression = annotation.value();
            if (StringUtils.isBlank(expression)) {
                handleDefaultLog(result, handlerMethod, args);
            } else if (expression.startsWith("#{")) { // SpEL 表达式
                // @OperatorLog("#{'pageNum='+#criteria.pageNum+' resultCode=' + #$.code+' method='+#th}")

                StandardEvaluationContext context = new StandardEvaluationContext();
                Map<String, Object> map = getParamNameValueMap(handlerMethod.getMethodParameters(), args);
                context.setVariables(map);
                context.setVariable("$", result);
                context.setVariable("method", handlerMethod);

                ExpressionParser parser = new SpelExpressionParser();
                Object value = parser.parseExpression(expression, new TemplateParserContext()).getValue(context);
                handleCustomLog(String.valueOf(value), result, handlerMethod, args);

            } else { // 自定义的写法
                // @OperatorLog("pageNum={criteria.pageNum} resultCode={$.code}")

                Map<String, Object> map = getParamNameValueMap(handlerMethod.getMethodParameters(), args);
                LogExpressionParser logExpressionParser = new ControllerLogExpressionParser(map, result, handlerMethod);
                String content = logExpressionParser.parse(expression);
                handleCustomLog(content, result, handlerMethod, args);
            }
        }
    }

    private void handleCustomLog(String content, Object result, ServletInvocableHandlerMethod handlerMethod, Object[]
            args) {
    }


    private void handleDefaultLog(Object result, ServletInvocableHandlerMethod handlerMethod, Object[] args) {
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

}
