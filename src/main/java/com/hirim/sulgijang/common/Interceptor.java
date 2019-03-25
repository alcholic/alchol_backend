package com.hirim.sulgijang.common;

import com.hirim.sulgijang.models.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
        if(auth != null) {
            if(request.getSession().getAttribute("user") == null) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        if(!request.getRequestURI().contains("api") && !request.getRequestURI().contains("login") && request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");
            modelAndView.addObject("user", user);
        }
    }
}
