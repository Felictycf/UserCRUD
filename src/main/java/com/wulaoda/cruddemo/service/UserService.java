package com.wulaoda.cruddemo.service;

import com.wulaoda.cruddemo.entity.UserEntity;
import com.wulaoda.cruddemo.req.SysUserReq;
import com.wulaoda.cruddemo.req.UserReq;
import com.wulaoda.cruddemo.req.UserSaveReq;
import com.wulaoda.cruddemo.resp.CommonResp;
import com.wulaoda.cruddemo.resp.PageResp;
import com.wulaoda.cruddemo.resp.SysUserResp;

public interface UserService {

    PageResp<UserEntity> getList(UserReq userReq);

    void delete(Long id);

    void save(UserSaveReq req);

    SysUserResp login(SysUserReq req);
}
