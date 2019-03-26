package com.hirim.sulgijang.common;

import com.hirim.sulgijang.models.User;

import javax.servlet.http.HttpServletRequest;

public class LoginService {

    public static User UserBySession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
