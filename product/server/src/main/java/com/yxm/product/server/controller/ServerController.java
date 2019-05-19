package com.yxm.product.server.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxm
 * @date 2019/4/20 0:16:16
 */
@RestController
public class ServerController {
    @GetMapping("/msg")
   public String msg(){
       return "this is product server mmsg";
   }
}
