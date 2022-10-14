package com.wulaoda.cruddemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wulaoda.cruddemo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
