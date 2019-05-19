package com.yxm.order.repository;
import com.yxm.order.OrderApplicationTests;
import com.yxm.order.entity.OrderMaster;
import com.yxm.order.enums.OrderStatusEnum;
import com.yxm.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;
/**
 * @author yxm
 * @date 2019/4/18 0:39:39
 */
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests{

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("1886131241241");
        orderMaster.setBuyerAddress("总部");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster result = repository.save(orderMaster);
        Assert.assertTrue(result != null);
    }
}