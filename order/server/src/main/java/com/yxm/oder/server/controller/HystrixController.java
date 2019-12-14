package com.yxm.oder.server.controller;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1000"),
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "1000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "30")
    })
   /* @HystrixCommand*/
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number){
        if(number % 2 == 0){
          return "success";
        }
        /**
         * 1.我们先使用restTemplate来调用商品列表
         */
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://127.0.0.1:8081/product/listForOrder", Arrays.asList("157875196366160022"),String.class);
      /*  throw new RuntimeException("发送异常了!");*/
    }
    /**
     * 出故障后调用方法
     * @return
     */
    private String fallback(){
        return "太拥挤了，请稍后再试~~~";
    }

    private String defaultFallback(){
        return "默认提示:太拥挤了，请稍后再试~~~";
    }
}
