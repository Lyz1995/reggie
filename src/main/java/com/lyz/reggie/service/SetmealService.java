package com.lyz.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.reggie.dto.SetmealDto;
import com.lyz.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
    public void removeWithDish(List<Long> ids);
}
