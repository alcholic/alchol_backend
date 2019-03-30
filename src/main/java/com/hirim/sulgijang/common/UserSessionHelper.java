package com.hirim.sulgijang.common;

import com.hirim.sulgijang.models.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class UserSessionHelper {
    public final static String USER_INFO_SESSION = "userInfo";

    public static UserInfo getUserInfo(HttpServletRequest request) {
        return (UserInfo) request.getAttribute("userInfo");
    }

}
