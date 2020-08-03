package com.wsf.demo.controller;

import com.wsf.demo.entity.User;
import com.wsf.demo.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
@Api
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    /**
     * POST登录
     * @param username
     * @param pwd
     * @return
     */
    @PostMapping(value = "/loginVali")
    public String loginVali(HttpSession session, HttpServletRequest request, String username, String pwd) {

        // 添加用户认证信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, pwd);

        // 进行验证，这里可以捕获异常，然后返回对应信息
        SecurityUtils.getSubject().login(usernamePasswordToken);
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        request.setAttribute("username", user.getUsername());
        session.setAttribute("user", user);
        return "list";
    }

    /**
     * 添加用户
     * @param username
     * @param pwd
     * @return
     */
    @PostMapping(value = "/add")
    @RequiresPermissions("add")
    public String add(String username, String pwd) {
        User user = new User();
        user.setUsername(username);
        user.setPwd(pwd);
        user = this.userService.add(user);
        return "add is ok! \n" + user;
    }

    /**
     * 修改用户
     * @return
     */
    @PostMapping(value = "/edit")
    @RequiresPermissions("edit")
    public String edit() {
        return "edit page!";
    }

    @GetMapping(value = "/list")
    @RequiresPermissions("list")
    public String list() {
        return "WEB-INF/page/list";
    }

    @GetMapping(value = "/del")
    @RequiresPermissions("del")
    public String del() {
        return "delete page!";
    }

    @GetMapping(value = "/error")
    public String error() {
        return "error page!";
    }

    /**
     * 退出的时候是get请求，主要是用于退出
     * @return
     */
    @GetMapping(value = "/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "logout";
    }
}
