package com.yxm.product.dao;
import com.yxm.product.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
/**
 * @author yxm
 * @date 2019/4/14 13:52:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> lists = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(lists.size()>0);
    }

}