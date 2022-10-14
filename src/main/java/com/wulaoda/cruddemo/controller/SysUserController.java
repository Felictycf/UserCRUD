package com.wulaoda.cruddemo.controller;

import com.wulaoda.cruddemo.req.SysUserSaveReq;
import com.wulaoda.cruddemo.req.UserLoginReq;
import com.wulaoda.cruddemo.resp.CommonResp;
import com.wulaoda.cruddemo.resp.UserLoginResp;
import com.wulaoda.cruddemo.service.SysUserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;



    @PostMapping("register")
    //前端用json方式提交后端要用@RequestBody接收 content/Type:application/json
    //用form表单格式提交就什么都不用写 Content-Type : application/x-ww-form-urlencoded
    public CommonResp register(@Valid @RequestBody SysUserSaveReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        sysUserService.register(req);
        return resp;
    }


    @PostMapping("login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = sysUserService.login(req);
        resp.setContent(userLoginResp);
        return resp;
    }


}
