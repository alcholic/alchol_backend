package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.repositories.UserMapper;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    private final UserMapper userMapper;

    public HomeController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("test")
    @ResponseBody
    public User test() {
        return new User(userMapper.test(), "asd");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String home() {
        return "hi";
    }
}
