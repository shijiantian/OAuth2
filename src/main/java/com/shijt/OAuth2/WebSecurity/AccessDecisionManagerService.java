package com.shijt.OAuth2.WebSecurity;

import com.shijt.OAuth2.Utils.ClassUtil;
import com.shijt.OAuth2.vo.Role;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class AccessDecisionManagerService implements AccessDecisionManager {

    /**
     * 判断用户是否有访问url的权限
     * @param authentication
     * @param object
     * @param configAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(null==configAttributes||configAttributes.size()<1)return;
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        ConfigAttribute cfgAttr;
        String resourceId;
        AntPathRequestMatcher matcher;
        Iterator<ConfigAttribute> iterator=configAttributes.iterator();
        for(;iterator.hasNext();){
            cfgAttr=iterator.next();
            resourceId=cfgAttr.getAttribute();
            for(GrantedAuthority ga:authentication.getAuthorities()){
                if(ClassUtil.CanonicalNameIsTheSame(ga,Role.class)){
                    Role role=ClassUtil.serializableObjectCast(ga);
                    if(role.getResourceIds().contains(resourceId.trim())){
                        return;
                    }
                }else if (ga.getAuthority().equals("ROLE_ANONYMOUS")) {
                    //未登录只允许访问 login 页面
                    matcher = new AntPathRequestMatcher("/login");
                    if (matcher.matches(request)) {
                        return;
                    }
                }
            }

        }
        throw new AccessDeniedException(" access denied!");
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
