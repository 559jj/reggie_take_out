package com.zjj.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjj.reggie.entity.Category;
import com.zjj.reggie.entity.Setmeal;
import com.zjj.reggie.mapper.CategoryMapper;
import com.zjj.reggie.mapper.SetmealMapper;
import com.zjj.reggie.service.CategoryService;
import com.zjj.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
