package com.yxm.oder.server;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
/**
 * @author yxm
 * @date 2019/5/26 22:23:23
 */
@Component
public class MqSenderTest extends OrderApplicationTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue","now "+new Date());
        System.out.println("send");
    }

    /**
     * 下订单
     */
    @Test
    public void sendOrder(){
        amqpTemplate.convertAndSend("myOrder","computer","now "+new Date());
        System.out.println("sendComputer");
    }
}
