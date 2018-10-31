package com.shijt.OAuth2.WebSecurity;

import com.shijt.OAuth2.commons.GlobalConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityInterceptorFilter securityInterceptorFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //匹配以下规则的路径不需要登录
                .antMatchers(GlobalConsts.login_no_need+"*").permitAll()
                //匹配以下规则的路径需要授权登录
                .antMatchers(GlobalConsts.login_need+"*").authenticated()
                //其他uri一律需要授权
                .anyRequest().authenticated()
                .and()
                //定义需要登录时跳转的登录页面
                .formLogin()
                .permitAll() //登录页面用户任意访问
                .and()
                .logout()
                .permitAll() //注销行为任意访问
                .and()
                .addFilterBefore(securityInterceptorFilter, FilterSecurityInterceptor.class);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }
}
