package com.shijt.OAuth2.WebSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

@Service
public class SecurityInterceptorFilter extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private FilterInvocationSecurityMetadataSourceService securityMetadataSourceService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Autowired
    public void setAccessDecisionManagerService(AccessDecisionManagerService accessDecisionManagerService) {
        super.setAccessDecisionManager(accessDecisionManagerService);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        new一个包含被拦截url的FilterInvocation
//        里面调用FilterInvocationSecurityMetadataSourceService的getAttributes(Object object)
//        这个方法获取FilterInvocation对应的所有权限
//        再调用AccessDecisionManagerService的decide方法来校验用户的权限是否足够
        FilterInvocation filterInvocation= new FilterInvocation(servletRequest,servletResponse,filterChain);
        InterceptorStatusToken token=super.beforeInvocation(filterInvocation);
        try{
//            执行拦截器
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(),filterInvocation.getResponse());
        }finally {
            super.afterInvocation(token,null);
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSourceService;
    }
}
