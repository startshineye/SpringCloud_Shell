package com.yxm.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yxm
 * @date 2019/5/12 11:08:08
 */
@FeignClient(name = "product")
public interface ProductClient {
    @GetMapping("/msg")
    String productMsg();
}
