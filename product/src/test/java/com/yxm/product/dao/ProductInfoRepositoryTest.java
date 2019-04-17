package com.yxm.product.dao;
import com.yxm.product.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
/**
 * @author yxm
 * @date 2019/4/14 13:40:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;
    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> lists = repository.findByProductStatus(0);
        Assert.assertTrue(lists.size()>0);
    }

}