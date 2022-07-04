package com.zjj.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjj.reggie.dto.DishDto;
import com.zjj.reggie.entity.Dish;
import com.zjj.reggie.entity.Employee;

public interface DishService extends IService<Dish>{

    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息对应的口味
    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
