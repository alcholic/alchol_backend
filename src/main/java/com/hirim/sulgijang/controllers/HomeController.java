package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.utils.UserSessionUtils;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("test")
    @ResponseBody
    public CommonResponse test(HttpServletRequest request) {
//        User user = UserSessionUtils.UserBySession(request);
//        return CommonResponse.successObject(new User(userRepository.test(), "asd"));
        return CommonResponse.success();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String home() {
        return "hi";
    }
}
