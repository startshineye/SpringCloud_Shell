package com.yxm.order.controller;

import com.yxm.order.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author yxm
 * @date 2019/4/20 0:18:18
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;


    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        String msg = productClient.productMsg();
        log.info("getProductMsg()",msg);
        return msg;
    }
}
