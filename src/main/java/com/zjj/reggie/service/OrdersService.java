package com.zjj.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjj.reggie.common.R;
import com.zjj.reggie.entity.Orders;


/**
 * 订单表(Orders)表服务接口
 *
 * @author makejava
 * @since 2022-10-07 17:34:27
 */
public interface OrdersService extends IService<Orders> {

    void submit(Orders orders);

}
