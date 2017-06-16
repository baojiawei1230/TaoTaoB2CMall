package com.zeus.soa.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限过滤器
 *
 * @Author Alex_Bao
 * @create 2017-06-14
 * create by IntelliJ IDEA
 */
public class PermissionFilter implements Filter{
    private String[] execludesUrl;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String[] excludesUrls = filterConfig.getInitParameter("excludesUrl").split(",");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;



    }

    @Override
    public void destroy() {

    }
}
