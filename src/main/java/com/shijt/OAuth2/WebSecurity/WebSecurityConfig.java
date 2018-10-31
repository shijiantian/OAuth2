package com.shijt.OAuth2.WebSecurity;

import com.shijt.OAuth2.commons.GlobalConsts;
import com.shijt.OAuth2.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //匹配以下规则的路径不需要登录
                .antMatchers(HttpMethod.OPTIONS, GlobalConsts.login_no_need+"*").permitAll()
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
                .permitAll(); //注销行为任意访问
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //从数据库查询用户数据并设置密码的加密机制
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


}
