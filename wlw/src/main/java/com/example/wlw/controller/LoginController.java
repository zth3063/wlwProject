package com.example.wlw.controller;


import com.example.wlw.entity.Login;
import com.example.wlw.serviceimpl.LoginServiceimpl;
import com.example.wlw.util.HttpMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.druid.util.Utils.md5;

@RestController
@Api(value = "用户登陆" , tags = {"用户登陆"} ,description = "Login Controller类")
public class LoginController {
    public static int alluser;
    public static List<Login> AllLogin = new ArrayList<>();
    @Autowired
    private LoginServiceimpl loginServiceimpl;
    @ApiOperation(value = "用户登陆",notes = "属性uid、upassword必填，返回属性messagestate为true表示成功")
    @PostMapping("/login")
    public HttpMessage<Login> findbyuid(@RequestBody Login login){
        HttpMessage<Login> ret  = new HttpMessage<>();
        Login login2 = loginServiceimpl.findbyuid(login.uid);
        if(login2 == null){
            ret.setstate(false);
            return ret;
        }
        login.upassword = md5(login.uid+login.upassword);
        if(login2.upassword.equals(login.upassword)){
            ret.setstate(true);
            ret.message = new Login();
            ret.setMessage(login2);
            AllLogin.add(login2);
//            ret.token = JwtUtil.CreateToken(login);
            if(AllLogin.contains(login2)){
                alluser++;
            }
            return ret;
        }
        else{
            ret.setstate(false);
            return ret;
        }
    }
    @ApiOperation(value = "用户注册",notes = "属性uname、upassword必填，返回属性message为分配uid，属性messagestate为true表示成功")
    @PostMapping("/regist")
    public HttpMessage<Integer> CreateAccount(@RequestBody Login login){
        HttpMessage<Integer> ret  = new HttpMessage<Integer>();
        login.uid = String.valueOf(loginServiceimpl.MAXuid() + 1);
        login.upassword = md5(login.uid+login.upassword);
        ret.setstate(true);
        try {
            loginServiceimpl.insert(login);
        }catch (Exception e){
            ret.setstate(false);
        }
        ret.setMessage(Integer.parseInt(login.uid));
        return ret;
    }
    @ApiOperation(value = "修改用户信息",notes = "属性uid、uname、upassword必填，修改用户职为，密码，姓名都是这个接口,校验在前端进行，属性messagestate为true表示成功")
    @PostMapping("/changeUser")
    public HttpMessage<Integer> ChangeUser(@RequestBody Login login){
        HttpMessage<Integer> ret  = new HttpMessage<Integer>();
        String password = login.upassword;
        login.upassword = md5(login.uid+login.uoldpassword);
        Login login2 = loginServiceimpl.findbyuid(login.uid);
        if(login2 == null){
            ret.setstate(false);
            return ret;
        }
        try {
        if(login2.upassword.equals(login.upassword)){
            login.upassword = md5(login.uid+password);
            loginServiceimpl.Update(login);
            ret.setstate(true);
            return ret;
        }
        }catch (Exception e){
            ret.setstate(false);
        }
        ret.setMessage(Integer.parseInt(login.uid));
        return ret;
    }

    @ApiOperation(value = "查询用户总数",notes = "返回属性message为总数值，属性messagestate为true表示成功")
    @PostMapping("/CountUser")
    public HttpMessage<Integer> CountUser(){
        HttpMessage<Integer> ret  = new HttpMessage<Integer>();
        ret.setstate(true);
        try {
            ret.setMessage(loginServiceimpl.MAXuid());
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }

    @ApiOperation(value = "查询用户总数",notes = "返回属性message为总数值，属性messagestate为true表示成功")
    @PostMapping("/CountUserDay")
    public HttpMessage<Integer> CountUserDay(){
        HttpMessage<Integer> ret  = new HttpMessage<Integer>();
        ret.setstate(true);
        try {
            ret.setMessage(alluser);
        }catch (Exception e){
            ret.setstate(false);
        }
        return ret;
    }
}
