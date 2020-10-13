package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.entity.User;
import com.fivesix.fivesixserver.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LoginController {

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String username = HtmlUtils.htmlEscape(requestUser.getUserName());
        if (!username.equals("admin") || !requestUser.getPassword().equals("123")) {
            return new Result(400,"账户或密码错误");
        }else {
            return new Result(200,"登录成功");
        }
    }
}
