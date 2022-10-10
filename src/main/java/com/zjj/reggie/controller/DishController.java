package com.zjj.reggie.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjj.reggie.common.R;
import com.zjj.reggie.dto.DishDto;
import com.zjj.reggie.entity.Dish;
import com.zjj.reggie.service.DishFlavorService;
import com.zjj.reggie.service.DishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        return dishService.dishPage(page,pageSize,name);

    }

    //根据id查询菜品信息对应的口味
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){
        DishDto dishDto=dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
        dishService.updateWithFlavor(dishDto);
        return R.success("修改菜品成功");
    }

    //根据条件查询对应的菜品数据
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){
        return dishService.dishList(dish);

    }
    @DeleteMapping
    public R<String> delete(Long ids){
        dishService.removeWithFlavor(ids);
        return R.success("删除成功");
    }
}
