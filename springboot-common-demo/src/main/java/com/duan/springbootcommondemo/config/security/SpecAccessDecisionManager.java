package com.duan.springbootcommondemo.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * Created on 2018/10/18.
 *
 * @author DuanJiaNing
 */
@Component
public class SpecAccessDecisionManager implements AccessDecisionManager {

    // decide 方法是判定是否拥有权限的决策方法
    // authentication: 在 SpecUserDetailsService 中处理后以完成 “认证” 步骤并装载了用户信息
    // object: 包含客户端发起的请求的 requset 信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
    // configAttributes: 为 SpecFilterInvocationSecurityMetadataSource#getAttributes(Object object) 方法返回的结果
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        if (CollectionUtils.isEmpty(configAttributes)) {
            return; // 可以访问
        }

        for (ConfigAttribute func : configAttributes) {
            String attr = func.getAttribute();

            // authentication 为访问者拥有的权限，configAttributes 为访问 url 需要具备的权限，比对两者进行决策
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (attr.equals(ga.getAuthority())) {
                    return; // 可以访问
                }
            }
        }

        // 无权访问
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}