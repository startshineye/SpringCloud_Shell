package com.yxm.product.service;

import com.yxm.product.entity.ProductCategory;

import java.util.List;

/**
 * @author yxm
 * @date 2019/4/14 22:38:38
 */
public interface ProductCategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
