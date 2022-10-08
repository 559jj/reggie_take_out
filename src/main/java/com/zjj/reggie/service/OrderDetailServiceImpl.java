package com.zjj.reggie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjj.reggie.entity.OrderDetail;
import com.zjj.reggie.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
 * 订单明细表(OrderDetail)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 17:34:38
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}

