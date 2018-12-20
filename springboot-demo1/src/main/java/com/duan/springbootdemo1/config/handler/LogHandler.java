package com.duan.springbootdemo1.config.handler;

import com.duan.springbootdemo1.config.annotation.OperatorLog;
import com.duan.springbootdemo1.config.enums.OperatorLogMark;
import com.duan.springbootdemo1.config.handler.log.LogExpressionParser;
import com.duan.springbootdemo1.config.handler.log.OperatorLogDataParser;
import com.duan.springbootdemo1.config.handler.log.OperatorLogExpressionParser;
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
import java.util.List;
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
            String content = null;

            Map<String, Object> map = getParamNameValueMap(handlerMethod.getMethodParameters(), args);
            OperatorLogDataParser dataParser = new OperatorLogDataParser(map, result);

            if (StringUtils.isBlank(expression)) {
                handleDefaultLog(result, handlerMethod, args);
            } else if (expression.startsWith("#{")) { // SpEL 表达式
                // @OperatorLog("#{'pageNum='+#criteria.pageNum+' resultCode=' + #$.code+' method='+#th}")

                StandardEvaluationContext context = new StandardEvaluationContext();
                context.setVariables(map);
                context.setVariable("$", result);
                context.setVariable("method", handlerMethod);

                ExpressionParser parser = new SpelExpressionParser();
                Object value = parser.parseExpression(expression, new TemplateParserContext()).getValue(context);
                content = String.valueOf(value);

            } else { // 自定义的写法
                // @OperatorLog("pageNum={criteria.pageNum} resultCode={$.code}")

                LogExpressionParser logExpressionParser = new OperatorLogExpressionParser(map, dataParser, result, handlerMethod);
                content = logExpressionParser.parse(expression);
            }

            OperatorLogMark mark = annotation.mark();
            Object[] datas = null;
            if (annotation.data().length > 0) {
                datas = new Object[annotation.data().length];
                for (int i = 0; i < annotation.data().length; i++) {
                    datas[i] = dataParser.parseValue(annotation.data()[i]);
                }
            }

            handleCustomLog(content, mark, datas, result, handlerMethod, args);

        }
    }

    private void handleCustomLog(String content, OperatorLogMark mark, Object[] datas, Object result,
                                 ServletInvocableHandlerMethod handlerMethod, Object[] args) {
        switch (mark) {
            case CHANNEL:
                insertChannelOperatorLog(content, datas);
                break;
        }
    }

    private void insertChannelOperatorLog(String content, Object[] datas) {

        if (datas != null && datas.length == 1) {
            Object data = datas[0];
            if (data instanceof List) {
                for (Object item : ((List) data)) {
                    saveOperatorLog((Integer) item, content);
                }
            } else if (data instanceof Integer) {
                saveOperatorLog((Integer) data, content);
            }
        }
    }

    private void saveOperatorLog(Integer channelId, String content) {
        // access db
    }

    private void handleDefaultLog(Object result, ServletInvocableHandlerMethod handlerMethod, Object[] args) {
        // handle default
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
