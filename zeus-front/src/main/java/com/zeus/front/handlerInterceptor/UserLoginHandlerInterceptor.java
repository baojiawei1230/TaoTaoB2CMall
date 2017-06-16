package com.zeus.front.handlerInterceptor;

import com.zeus.common.util.CookieUtils;
import com.zeus.front.bean.User;
import com.zeus.front.service.UserService;
import com.zeus.front.threadLocal.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginHandlerInterceptor implements HandlerInterceptor {

    public static final String COOKIE_NAME = "TT_TOKEN";

    private static final String LOGIN_URL = "http://sso.taotao.com/user/login.html";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取cookie中token
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
        if (StringUtils.isEmpty(token)) {
            // 未登录
            response.sendRedirect(LOGIN_URL);
            UserThreadLocal.set(null);
            return false;
        }

        User user = this.userService.queryUserByToken(token);
        if (null == user) {
            // 未登录
            response.sendRedirect(LOGIN_URL);
            UserThreadLocal.set(null);
            return false;
        }
        // 已经处于登录状态
        UserThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // 视图渲染完成后执行
    }

}
