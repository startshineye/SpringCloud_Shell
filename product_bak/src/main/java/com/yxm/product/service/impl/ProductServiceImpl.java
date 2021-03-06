package com.yxm.product.service.impl;
import com.yxm.product.dto.CartDTO;
import com.yxm.product.dao.ProductInfoRepository;
import com.yxm.product.entity.ProductInfo;
import com.yxm.product.enums.ProductStatusEnum;
import com.yxm.product.enums.ResultEnum;
import com.yxm.product.exception.ProductException;
import com.yxm.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return repository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<CartDTO> cartDTOList) {
        /**
         * 遍历:查看是否存在
         */
        for (CartDTO cartDTO:cartDTOList){
            Optional<ProductInfo> productInfoOptional = repository.findById(cartDTO.getProductId());
            //商品不存在
            if(!productInfoOptional.isPresent()){
             throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //商品存在-库存错误
            ProductInfo productInfo = productInfoOptional.get();
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROE);
            }
            //保存
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
}


































