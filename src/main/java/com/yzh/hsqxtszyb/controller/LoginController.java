package com.yzh.hsqxtszyb.controller;

import com.yzh.hsqxtszyb.pojo.User;
import com.yzh.hsqxtszyb.result.Result;
import com.yzh.hsqxtszyb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;



@Controller
public class LoginController {

    UserService userService=new UserService();
    @ResponseBody
    @CrossOrigin
    @PostMapping(value = "api/login")
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        com.yzh.hsqxtszyb.model.User myuser = new com.yzh.hsqxtszyb.model.User(username, requestUser.getPassword());
        return new Result(userService.userIsExist(myuser));
    }
}
