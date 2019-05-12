package com.yxm.order.service.impl;
import com.yxm.order.dto.OrderDTO;
import com.yxm.order.entity.OrderMaster;
import com.yxm.order.enums.OrderStatusEnum;
import com.yxm.order.enums.PayStatusEnum;
import com.yxm.order.repository.OrderMasterRepository;
import com.yxm.order.service.OrderService;
import com.yxm.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yxm
 * @date 2019/4/18 23:42:42
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //1.TODO 查询商品信息(调用商品服务)
        //2.TODO 计算总价
        //3.TODO 扣除库存(调用商品服务)
        //4. 订单入库
        OrderMaster orderMaster = new OrderMaster();
        //先设置主键 会copy到orderMaster
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
