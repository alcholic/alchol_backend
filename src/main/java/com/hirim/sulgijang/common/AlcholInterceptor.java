package com.hirim.sulgijang.common;


import com.google.common.base.Strings;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.common.utils.JsonUtils;

import com.hirim.sulgijang.models.UserAgent;
import com.hirim.sulgijang.models.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AlcholInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        setAgent(request);
        Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);
        if(auth != null) {
            final String user = request.getHeader("user");
            if (StringUtils.isEmpty(user))
                throw new AlcholException("잘못된 유저 입니다.");

            final User userObject = JsonUtils.jsonToPojo(user, User.class);
            request.setAttribute("user", userObject);
        }

        return super.preHandle(request, response, handler);
    }

    private void setAgent(HttpServletRequest request) {
        final String headerAgent = request.getHeader("agent");
        if(!Strings.isNullOrEmpty(headerAgent)) {
            final UserAgent userAgent = JsonUtils.jsonToPojo(headerAgent, UserAgent.class);
            final String accessToken = request.getHeader("accessToken");
            request.setAttribute(UserSessionHelper.USER_INFO_SESSION, new UserInfo(userAgent, accessToken));
        }
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
