package com.yxm.oder.server.message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * 接收mq消息
 * @author yxm
 * @date 2019/5/26 19:53:53
 */
@Slf4j
@Component
public class MQReceiver {
    /**
     * 如何接收消息呢?我们使用注解:RabbitListener
     */
    //@RabbitListener(queues = "myQueue")  //手动创建队列
    //@RabbitListener(queuesToDeclare = @Queue("myQueue1")) //自动创建队列
    @RabbitListener(bindings =@QueueBinding(value =@Queue("myQueue"),exchange =@Exchange("myExchange")))
    public void process(String message){
         log.info("MQReceiver:{}",message);
    }

    /**
     * 数码服务接收消息
     * @param message
     */
    @RabbitListener(bindings =@QueueBinding(
            exchange =@Exchange("myOrder"),
            key = "computer",
            value =@Queue("computerOrder")
            ))
    public void processComputer(String message){
        log.info("Computer MQReceiver:{}",message);
    }

    /**
     * 水果服务接收消息
     * @param message
     */
    @RabbitListener(bindings =@QueueBinding(
          exchange =@Exchange("myOrder"),
            key = "fruit",
            value =@Queue("fruitOrder")
         ))
    public void processFruit(String message){
        log.info("Fruit MQReceiver:{}",message);
    }
}
