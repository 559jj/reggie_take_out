package com.zjj.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjj.reggie.common.BaseContext;
import com.zjj.reggie.common.R;
import com.zjj.reggie.entity.Setmeal;
import com.zjj.reggie.entity.ShoppingCart;
import com.zjj.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController{
    @Autowired
    private ShoppingCartService shoppingCartService;
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.add(shoppingCart);
    }
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        queryWrapper.orderByDesc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
        return R.success(list);
    }
    @DeleteMapping("/clean")
    public R<String> delete(){
        return shoppingCartService.delete();
    }
}

