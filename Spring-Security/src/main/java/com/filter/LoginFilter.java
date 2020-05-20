package com.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter init ......");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("path-->"+request.getRequestURI());
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("time filter 耗时:"+ (new Date().getTime() - start));
    }

    @Override
    public void destroy() {
        System.out.println("LoginFilter destroy ......");
    }
}
