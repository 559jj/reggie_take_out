package com.zjj.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjj.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;


/**
 * 订单表(Orders)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-07 17:34:27
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}


