package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.entity.User;
import com.fivesix.fivesixserver.result.Result;
import com.fivesix.fivesixserver.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;


@RestController
public class UserController {

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/login")
    public Result login(@RequestBody User requestUser,@RequestParam(value = "rememberMe") boolean rememberMe) {
        String requestUserName = HtmlUtils.htmlEscape(requestUser.getUsername());
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(requestUserName,requestUser.getPassword());
        usernamePasswordToken.setRememberMe(rememberMe);
        try{
            subject.login(usernamePasswordToken);
            return new Result(200,"login successfully");

        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new Result(400,"账号或密码错误");
        }
    }

    @PostMapping("/api/register")
    public Result register(@RequestBody User user) {
        String username = HtmlUtils.htmlEscape(user.getUsername());
        user.setUsername(username);
        //生成盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt(salt);
        //设置迭代次数
        int times = 2;
        //生成加密的密码
        String encodedPassword = new SimpleHash("md5",user.getPassword(),salt,times).toString();
        user.setPassword(encodedPassword);

        try {
            userService.register(user);
            return new Result(200,"register successfully.");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(400,e.getMessage());
        }
    }

    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(200,"登出成功");
    }

    @GetMapping("api/authentication")
    public Result authenticate() {
        return new Result(200,"认证成功");
    }

    @GetMapping("api/admin/user")
    public List<User> getAllUsers() {
        return userService.listAll();
    }

    @PostMapping("api/admin/user/delete")
    public Result delete(@RequestBody User user) {
        try {
            userService.deleteUser(user);
            return new Result(200,"删除用户成功");
        }catch (Exception e) {
            return new Result(400,"删除用户失败");
        }
    }

    @PutMapping("api/admin/user/update")
    public Result update(@RequestBody User user) {
        try{
            userService.updateUser(user);
            return new Result(200,"更新用户成功");
        }catch (Exception e){
            return new Result(400,"更新用户失败");
        }
    }
}
