package com.zjj.reggie.controller;

import com.zjj.reggie.common.R;
import com.zjj.reggie.entity.Orders;
import com.zjj.reggie.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单表(Orders)表控制层
 *
 * @author makejava
 * @since 2022-10-07 17:34:26
 */
@RestController
@RequestMapping("order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        ordersService.submit(orders);
        return R.success("下单成功");
    }
}

