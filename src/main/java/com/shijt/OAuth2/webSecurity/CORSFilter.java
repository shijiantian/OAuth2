package com.shijt.OAuth2.webSecurity;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域配置
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin","*");
//        response.setHeader("Access-Control-Allow-Methods","*");
//        response.setHeader("Access-Control-Allow-Headers","*");
        //设置为*在firefox中无法跨域，需要明确指出Methods与Headers
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, " +
                "authorization, Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, " +
                "Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
        response.setHeader("Access-Control-Expose-Headers","Location,Content-Disposition");

        //预检请求的有效期
        response.setHeader("Access-Control-Max-Age","3600");

        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(req,res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
