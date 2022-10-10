package com.zjj.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjj.reggie.common.R;
import com.zjj.reggie.dto.DishDto;
import com.zjj.reggie.entity.Dish;
import com.zjj.reggie.entity.Employee;

import java.util.List;

public interface DishService extends IService<Dish>{

     void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息对应的口味
     DishDto getByIdWithFlavor(Long id);

     void updateWithFlavor(DishDto dishDto);


    void removeWithFlavor(Long ids);

    R<List<DishDto>> dishList(Dish dish);

    R<Page> dishPage(int page, int pageSize, String name);

}
