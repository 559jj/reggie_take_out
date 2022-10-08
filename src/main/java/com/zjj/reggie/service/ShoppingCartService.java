package com.zjj.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjj.reggie.common.R;
import com.zjj.reggie.entity.ShoppingCart;


/**
 * 购物车(ShoppingCart)表服务接口
 *
 * @author makejava
 * @since 2022-10-07 16:27:07
 */
public interface ShoppingCartService extends IService<ShoppingCart> {

    R<ShoppingCart> add(ShoppingCart shoppingCart);

    R<String> delete();

}
