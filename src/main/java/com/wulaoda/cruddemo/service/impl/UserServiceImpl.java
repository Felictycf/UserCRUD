package com.wulaoda.cruddemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wulaoda.cruddemo.entity.UserEntity;
import com.wulaoda.cruddemo.mapper.UserMapper;
import com.wulaoda.cruddemo.req.SysUserReq;
import com.wulaoda.cruddemo.req.UserReq;
import com.wulaoda.cruddemo.req.UserSaveReq;
import com.wulaoda.cruddemo.resp.CommonResp;
import com.wulaoda.cruddemo.resp.PageResp;
import com.wulaoda.cruddemo.resp.SysUserResp;
import com.wulaoda.cruddemo.service.UserService;
import com.wulaoda.cruddemo.utils.CopyUtil;
import com.wulaoda.cruddemo.utils.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    @Override
    public PageResp<UserEntity> getList(UserReq userReq) {

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(userReq.getName())){
            queryWrapper.lambda().eq(UserEntity::getName,userReq.getName());
        }
        if(!ObjectUtils.isEmpty(userReq.getPhone())){
            queryWrapper.lambda().eq(UserEntity::getPhone,userReq.getPhone());
        }
        if(!ObjectUtils.isEmpty(userReq.getCity())){
            queryWrapper.lambda().eq(UserEntity::getCity,userReq.getCity());
        }

        Page<UserEntity> page = new Page<>(userReq.getPage(), userReq.getSize());
        IPage<UserEntity> userEntityIPage = userMapper.selectPage(page, queryWrapper);
        PageResp<UserEntity> pageResp = new PageResp<>();
        pageResp.setTotal(userEntityIPage.getTotal());
        pageResp.setList(userEntityIPage.getRecords());
        return pageResp;
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void save(UserSaveReq req) {
        UserEntity entity = CopyUtil.copy(req, UserEntity.class);
        if(ObjectUtils.isEmpty(req.getId())){
            entity.setId(snowFlake.nextId());
            userMapper.insert(entity);
        }else {
            userMapper.updateById(entity);
        }
    }

    @Override
    public SysUserResp login(SysUserReq req) {
        return null;
    }
}
