package com.yxm.oder.server;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@EnableFeignClients(basePackages = "com.yxm.product.client")
/*@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker*/
@SpringCloudApplication
@ComponentScan(basePackages = "com.yxm")
@EnableHystrixDashboard
public class OrderApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}
