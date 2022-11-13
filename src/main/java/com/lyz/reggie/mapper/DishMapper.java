package com.lyz.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyz.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
