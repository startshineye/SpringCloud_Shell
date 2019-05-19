package com.yxm.order.repository;
import com.yxm.order.OrderApplicationTests;
import com.yxm.order.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;
/**
 * @author yxm
 * @date 2019/4/18 0:44:44
 */
@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests{

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void testSave(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12367");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http://xxx.com");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(2);
        OrderDetail result = repository.save(orderDetail);
        Assert.assertTrue(result != null);
    }

}