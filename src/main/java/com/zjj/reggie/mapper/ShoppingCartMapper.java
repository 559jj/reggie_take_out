package com.zjj.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjj.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;


/**
 * 购物车(ShoppingCart)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-07 16:27:03
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}


