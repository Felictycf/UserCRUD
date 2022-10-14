package com.wulaoda.cruddemo.controller;

import com.wulaoda.cruddemo.entity.UserEntity;
import com.wulaoda.cruddemo.req.SysUserReq;
import com.wulaoda.cruddemo.req.UserReq;
import com.wulaoda.cruddemo.req.UserSaveReq;
import com.wulaoda.cruddemo.resp.CommonResp;
import com.wulaoda.cruddemo.resp.PageResp;
import com.wulaoda.cruddemo.resp.SysUserResp;
import com.wulaoda.cruddemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping("/getList")
    public CommonResp getList(UserReq userReq){
        
        CommonResp<PageResp<UserEntity>> resp = new CommonResp<>();
        PageResp<UserEntity> list = userService.getList(userReq);
        resp.setContent(list);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp<UserEntity> resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody UserSaveReq req){
        CommonResp<UserEntity> resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }


    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody SysUserReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<SysUserResp> resp = new CommonResp<>();
        SysUserResp sysUserResp = userService.login(req);
        resp.setContent(sysUserResp);
        return resp;
    }


}
