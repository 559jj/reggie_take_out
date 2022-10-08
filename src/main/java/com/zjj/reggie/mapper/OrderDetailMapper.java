package com.zjj.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjj.reggie.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;


/**
 * 订单明细表(OrderDetail)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-07 17:34:38
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}


