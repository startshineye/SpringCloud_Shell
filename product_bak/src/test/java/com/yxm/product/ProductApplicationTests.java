package com.yxm.product;

import com.yxm.product.dao.ProductInfoRepository;
import com.yxm.product.entity.ProductInfo;
import com.yxm.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApplicationTests {

	@Autowired
	private ProductInfoRepository repository;

	@Autowired
	private ProductService productService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findByProductIdIn() throws Exception{
		//List<ProductInfo> byProductIdIn = repository.findByProductIdIn(Arrays.asList("157875196366160022", "157875227953464068"));
		List<ProductInfo> byProductIdIn = productService.findList(Arrays.asList("157875196366160022", "157875227953464068"));
		Assert.assertTrue(byProductIdIn.size()>0);
		System.out.println(byProductIdIn.toString());
	}
}
