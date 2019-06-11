package com.yxm.oder.server.controller;

import com.yxm.oder.server.dto.OrderDTO;
import com.yxm.oder.server.entity.OrderDetail;
import com.yxm.oder.server.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 消息生产者
 * @author yxm
 * @date 2019/6/3 22:58:58
 */
@RestController
public class SendMessageController {
    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process(){
         String message = "now " + new Date();
         streamClient.input().send(MessageBuilder.withPayload(message).build());
    }

    @GetMapping("/sendMessageForObject")
    public void processObject(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
