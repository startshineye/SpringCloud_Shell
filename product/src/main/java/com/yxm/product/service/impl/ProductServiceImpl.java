package com.yxm.product.service.impl;
import com.yxm.product.dao.ProductInfoRepository;
import com.yxm.product.entity.ProductInfo;
import com.yxm.product.enums.ProductStatusEnum;
import com.yxm.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author yxm
 * @date 2019/4/14 22:32:32
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
