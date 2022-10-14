package com.wulaoda.cruddemo.service;

import com.wulaoda.cruddemo.req.SysUserSaveReq;
import com.wulaoda.cruddemo.req.UserLoginReq;
import com.wulaoda.cruddemo.resp.UserLoginResp;

public interface SysUserService {
    void register(SysUserSaveReq req);

    UserLoginResp login(UserLoginReq req);
}
