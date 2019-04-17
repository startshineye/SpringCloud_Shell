package com.yxm.product.service;
import com.yxm.product.entity.ProductInfo;
import java.util.List;
/**
 * @author yxm
 * @date 2019/4/14 22:29:29
 */
public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();
}
