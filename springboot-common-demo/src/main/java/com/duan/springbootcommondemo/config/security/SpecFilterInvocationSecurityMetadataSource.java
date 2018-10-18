package com.duan.springbootcommondemo.config.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created on 2018/10/18.
 *
 * @author DuanJiaNing
 */
@Component
public class SpecFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    // 此方法是为了判定用户请求的 url 是否在 url:role 表中，如果在，查到 role 及对应的权限 funcs，返回并在
    // SpecAccessDecisionManager#decide 方法中最终决定访问者是否拥有权限进行访问
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();

        List<Object> urlRelRole = new ArrayList<>(); // 从数据库查出所有 url 与 role 的对应关系
        for (Object urr : urlRelRole) {
            String url = ""; // urr.getUrl();
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
            if (matcher.matches(request)) {
                Integer roleCode = 1; // urr.getRoleId();
                List<ConfigAttribute> cs = new ArrayList<>(); // 根据 url 或 role 查询对应的 funcs
                return cs;

             /*   //当权限表权限的method为ALL时表示拥有此路径的所有请求方式权利。
                if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                    return;
                }*/
            }

        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}