package com.zjj.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjj.reggie.common.CustomException;
import com.zjj.reggie.entity.Category;
import com.zjj.reggie.entity.Dish;
import com.zjj.reggie.entity.DishFlavor;
import com.zjj.reggie.entity.Setmeal;
import com.zjj.reggie.mapper.CategoryMapper;
import com.zjj.reggie.mapper.DishFlavorMapper;
import com.zjj.reggie.service.CategoryService;
import com.zjj.reggie.service.DishFlavorService;
import com.zjj.reggie.service.DishService;
import com.zjj.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
    
}
