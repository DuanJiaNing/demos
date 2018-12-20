package com.duan.springbootdemo1.config.handler.log;

import org.springframework.web.method.HandlerMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2018/11/8.
 *
 * @author DuanJiaNing
 */
public class OperatorLogExpressionParser implements LogExpressionParser {

    private final Map<String, Object> paramNameValueMap;
    private final Object returnValue;
    private final HandlerMethod method;
    private final OperatorLogDataParser dataParser;
    private final Pattern pattern = Pattern.compile("\\{(\\w|\\$).*?\\}");

    public OperatorLogExpressionParser(Map<String, Object> paramNameValueMap, Object returnValue, HandlerMethod method) {
        this.paramNameValueMap = paramNameValueMap;
        this.returnValue = returnValue;
        this.method = method;
        this.dataParser = new OperatorLogDataParser(paramNameValueMap, returnValue);
    }

    public OperatorLogExpressionParser(Map<String, Object> paramNameValueMap, OperatorLogDataParser dataParser, Object returnValue, HandlerMethod method) {
        this.paramNameValueMap = paramNameValueMap;
        this.returnValue = returnValue;
        this.method = method;
        this.dataParser = dataParser;
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
    @Override
    public String parse(String expression) {
        Matcher matcher = pattern.matcher(expression);
        Map<String, String> replaceMap = new HashMap<>(matcher.groupCount());

        while (matcher.find()) {
            String str = matcher.group();

            // obj.name $ $.cc
            String exp = str.substring(1, str.length() - 1);
            replaceMap.put(str, String.valueOf(dataParser.parseValue(exp)));
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

}
