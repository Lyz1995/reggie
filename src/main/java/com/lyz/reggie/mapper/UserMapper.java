package com.lyz.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyz.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
