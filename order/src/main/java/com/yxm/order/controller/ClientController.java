package com.yxm.order.controller;

import com.yxm.order.client.ProductClient;
import com.yxm.order.dto.CartDTO;
import com.yxm.order.dto.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfo> productInfos = productClient.listForOrder(Arrays.asList("157875196366160022"));
        return productInfos.toString();
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
       productClient.decreaseStock(Arrays.asList(new CartDTO("157875196366160022",1)));
       return "ok";
    }

}
