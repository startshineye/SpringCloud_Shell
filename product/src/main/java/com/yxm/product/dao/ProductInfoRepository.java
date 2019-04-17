package com.yxm.product.dao;
import com.yxm.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yxm
 * @date 2019/4/14 12:47:47
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
     List<ProductInfo> findByProductStatus(Integer productStatus);
}
