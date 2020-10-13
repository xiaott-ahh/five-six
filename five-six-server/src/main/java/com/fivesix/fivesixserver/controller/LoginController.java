package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.result.Result;
import com.fivesix.fivesixserver.entity.User;


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
        String userName = requestUser.getUserName();
        userName = HtmlUtils.htmlEscape(userName);
        if (!userName.equals("admin")|| !requestUser.getPassword().equals("123456")) {

            String message = "账户或密码错误";
            System.out.println("test");
            return new Result(400,message);
        }else{
            String message = "登录成功";
            System.out.println("登录成功");
            return new Result(200,message);
        }
    }
}
