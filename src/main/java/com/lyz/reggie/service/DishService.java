package com.lyz.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.reggie.dto.DishDto;
import com.lyz.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);

    public DishDto getWithFlavorByid(Long id);

    void updateWithFlavor(DishDto dishDto);

    void deleteWithFlavor(DishDto dishDto);
}
