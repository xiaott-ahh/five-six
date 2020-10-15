package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.entity.User;
import com.fivesix.fivesixserver.result.Result;
import com.fivesix.fivesixserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String name = HtmlUtils.htmlEscape(requestUser.getUserName());
        if (!userService.exist(name)) return new Result(400,"该用户不存在");
        if (userService.getByName(name).getPassword() != requestUser.getPassword())
            return new Result(400,"密码错误");
        else return new Result(200,"登录成功");

    }
}
