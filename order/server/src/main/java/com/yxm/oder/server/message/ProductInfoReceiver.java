package com.yxm.oder.server.message;
import com.yxm.oder.server.utils.JsonUtil;
import com.yxm.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * @author yxm
 * @date 2019/6/13 0:23:23
 */
@Slf4j
@Component
public class ProductInfoReceiver {
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
           //message转换成ProductInfoOutput,其中ProductInfoOutput是product服务提供的对象,
          // 所以product服务在发送的时候也是发送ProductInfoOutput对象
        ProductInfoOutput productInfoOutput = (ProductInfoOutput)JsonUtil.fromJson(message, ProductInfoOutput.class);
        log.info("从队列{} 接收到消息:{}","productInfo",productInfoOutput);
    }
}
