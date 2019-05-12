package com.yxm.order.service;

import com.yxm.order.dto.OrderDTO;

/**
 * @author yxm
 * @date 2019/4/18 23:29:29
 */
public interface OrderService {
    /**
     * 订单服务主要是为了创建订单
     * 由于前端传递的参数
     */
    OrderDTO create(OrderDTO orderDTO);
}
