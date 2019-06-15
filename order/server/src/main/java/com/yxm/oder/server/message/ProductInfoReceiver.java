package com.yxm.oder.server.message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yxm.oder.server.utils.JsonUtil;
import com.yxm.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yxm
 * @date 2019/6/13 0:23:23
 */
@Slf4j
@Component
public class ProductInfoReceiver {
    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message){
           //message转换成ProductInfoOutput,其中ProductInfoOutput是product服务提供的对象,
          // 所以product服务在发送的时候也是发送ProductInfoOutput对象
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>)JsonUtil.fromJson(message,
                new TypeReference< List<ProductInfoOutput>>() {});
        log.info("从队列{} 接收到消息:{}","productInfo",productInfoOutputList);

        //存储到redis(key:商品的id   value:商品的库存)
        for (ProductInfoOutput productInfoOutput:productInfoOutputList){
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
