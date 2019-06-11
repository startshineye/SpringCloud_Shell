package com.yxm.oder.server.message;

import com.yxm.oder.server.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * @author yxm
 * @date 2019/6/3 22:52:52
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {
    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.OUTPUT)
    public String processInput(Object message){
      log.info("INPUT StreamReceiver:{}",message);
      return "SendTo:";
   }

    @StreamListener(StreamClient.OUTPUT)
    public void processOutput(String message){
        log.info("OUTPUT StreamReceiver:{}",message);
    }
}
