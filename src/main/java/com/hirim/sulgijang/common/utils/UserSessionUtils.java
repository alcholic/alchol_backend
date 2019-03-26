package com.hirim.sulgijang.common.utils;

import com.hirim.sulgijang.models.User;

import javax.servlet.http.HttpServletRequest;

public class UserSessionUtils {

    public static User UserBySession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
