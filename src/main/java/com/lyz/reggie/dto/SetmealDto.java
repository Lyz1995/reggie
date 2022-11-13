package com.lyz.reggie.dto;


import com.lyz.reggie.entity.Setmeal;
import com.lyz.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
