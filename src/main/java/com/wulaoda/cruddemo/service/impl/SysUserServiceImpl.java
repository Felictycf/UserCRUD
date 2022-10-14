package com.wulaoda.cruddemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wulaoda.cruddemo.entity.SysUserEntity;
import com.wulaoda.cruddemo.exception.BusinessException;
import com.wulaoda.cruddemo.exception.BusinessExceptionCode;
import com.wulaoda.cruddemo.mapper.SysUserMapper;
import com.wulaoda.cruddemo.req.SysUserSaveReq;
import com.wulaoda.cruddemo.req.UserLoginReq;
import com.wulaoda.cruddemo.resp.UserLoginResp;
import com.wulaoda.cruddemo.service.SysUserService;
import com.wulaoda.cruddemo.utils.CopyUtil;
import com.wulaoda.cruddemo.utils.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SnowFlake snowFlake;

    @Override
    public void register(SysUserSaveReq req) {
        SysUserEntity user = CopyUtil.copy(req, SysUserEntity.class);
        if(ObjectUtils.isEmpty(req.getId())){
            SysUserEntity userDb = selectByLoginName(req.getLoginName());
            if(ObjectUtils.isEmpty(userDb)){
//                新增
                user.setId(snowFlake.nextId());
                sysUserMapper.insert(user);
            } else {
                //用户名已存在
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        }else {
            //修改密码
            user.setLoginName(null);
            user.setPassword(null);
            sysUserMapper.updateById(user);
        }
    }

    //登陆
    @Override
    public UserLoginResp login(UserLoginReq req) {
        SysUserEntity userDb = selectByLoginName(req.getLoginName());
        if(ObjectUtils.isEmpty(userDb)){
            //用户名不存在
            throw  new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }else {
            if(userDb.getPassword().equals(req.getPassword())){
                //登陆成功
                UserLoginResp userLoginResp = CopyUtil.copy(userDb, UserLoginResp.class);
                return userLoginResp;
            }else {
                //密码不对
                throw  new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }

    //    查询loginname是否有注册的
    public SysUserEntity selectByLoginName(String loginName){
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserEntity::getLoginName,loginName);
        List<SysUserEntity> userEntityList = sysUserMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(userEntityList)){
            return null;
        }else {
            return userEntityList.get(0);
        }
    }

}
