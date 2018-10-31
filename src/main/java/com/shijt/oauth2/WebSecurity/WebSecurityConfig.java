package com.shijt.oauth2.WebSecurity;

import com.shijt.oauth2.commons.GlobalConsts;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //匹配以下规则的路径不需要登录
                .antMatchers(HttpMethod.OPTIONS,GlobalConsts.login_no_need+"*").permitAll()
                //匹配以下规则的路径需要授权登录
                .antMatchers(GlobalConsts.login_need+"*").authenticated()
                //其他uri一律需要授权
                .anyRequest().authenticated()
                .and()
            //定义需要登录时跳转的登录页面
            .formLogin()
                .and()
            .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()   //在内存中创建用户
                .withUser("admin")
                .password("{noop}admin")    //https://stackoverflow.com/questions/46999940/spring-boot-passwordencoder-error
                .roles("USER");
    }

}
