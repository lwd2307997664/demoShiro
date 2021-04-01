package com.yangxf.demoShiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 〈配置登录接口以及页面访问接口〉
 *
 * @author linwd
 * @create 2021/4/1
 * @since 1.0.0
 */
@RestController
public class UserController {

    /**
     * 登录接口
     * @param username
     * @param password
     * @param model
     * @return
     */
    @GetMapping("/doLogin")
    public String doLogin(String username, String password, Model model){
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        Subject subject= SecurityUtils.getSubject();
        try{
            subject.login(token);
        }catch (AuthenticationException e){
            model.addAttribute("error","用户或密码输入错误");
            return "login";
        }
        return "redirect:/index";
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    @GetMapping("/user")
    public String user(){
        return "user";
    }
}
