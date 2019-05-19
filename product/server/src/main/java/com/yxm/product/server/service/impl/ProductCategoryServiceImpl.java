package com.yxm.product.server.service.impl;
import com.yxm.product.server.dao.ProductCategoryRepository;
import com.yxm.product.server.entity.ProductCategory;
import com.yxm.product.server.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author yxm
 * @date 2019/4/14 22:39:39
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository repository;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }
}
