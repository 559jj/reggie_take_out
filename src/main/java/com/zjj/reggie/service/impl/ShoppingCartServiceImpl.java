package com.zjj.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjj.reggie.common.BaseContext;
import com.zjj.reggie.common.R;
import com.zjj.reggie.entity.ShoppingCart;
import com.zjj.reggie.mapper.ShoppingCartMapper;
import com.zjj.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 16:27:07
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {



    @Override
    public R<ShoppingCart> add(ShoppingCart shoppingCart) {
        //设置用户id，指定当前是哪个用户的购物车数据
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);
        //查询当前菜品或者套餐是否在购物车中
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,currentId);
        Long dishId=shoppingCart.getDishId();
        if (dishId==null) {
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }else {
            queryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getDishId());
        }
        ShoppingCart one = this.getOne(queryWrapper);
        if (one==null){
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            this.save(shoppingCart);
            return R.success(shoppingCart);
        }
        one.setNumber(one.getNumber()+1);
        this.updateById(one);
        return R.success(one);
    }

    @Override
    public R<String> delete() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        this.remove(queryWrapper);
        return R.success("清空购物车成功");
    }
}

