package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.commons.GlobalConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequestMapping(GlobalConsts.login_need)
public class LogoutController {

    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String refresh_token=request.getParameter("refresh_token");
        String redirect_dir=request.getParameter("redirect_dir");
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        OAuth2RefreshToken refreshToken=tokenStore.readRefreshToken(refresh_token);
        tokenStore.removeAccessTokenUsingRefreshToken(refreshToken);
        tokenStore.removeRefreshToken(refreshToken);
        response.setHeader("Location",redirect_dir);
    }
}
