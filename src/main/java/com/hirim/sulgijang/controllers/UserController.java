package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.UserSessionHelper;
import com.hirim.sulgijang.common.utils.CryptUtils;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.models.UserInfo;
import com.hirim.sulgijang.models.param.PushTokenParam;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.models.response.LoginResponse;
import com.hirim.sulgijang.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public CommonResponse login(HttpServletRequest request, @RequestBody User user) {
        final UserInfo userInfo = UserSessionHelper.getUserInfo(request);
        final String newToken = CryptUtils.encrypt(new Random().ints(3).toString());
        return Optional.ofNullable(userService.select(user.getSnsType(), user.getSnsId()))
                .map(u -> userService.isAuth(u.getUserId(), userInfo.getAccessToken())
                        ? CommonResponse.successObject(new LoginResponse(u.getUserId(), userService.addInfo(u.getUserId(),
                        userInfo.getDeviceId(), userInfo.getDeviceType(), userInfo.getAppVersion(), newToken)))
                        : CommonResponse.fail("잘못된 키입니다")
                )
                .orElseGet(() -> {
                    userService.add(user);
                    return CommonResponse.successObject(
                            new LoginResponse(
                                    user.getUserId(),
                                    userService.addInfo(user.getUserId(), userInfo.getDeviceId(),
                                            userInfo.getDeviceType(), userInfo.getAppVersion(), newToken))
                    );
                });
    }

    @PutMapping("/pushToken")
    public CommonResponse updatePushToken(HttpServletRequest request, @RequestBody PushTokenParam param) {
        final UserInfo userInfo = UserSessionHelper.getUserInfo(request);
        userService.updatePushToken(userInfo.getUserId(), param.getPushToken());
        return CommonResponse.success();
    }

}