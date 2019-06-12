package com.yxm.product.server.service.impl;
import com.rabbitmq.tools.json.JSONUtil;
import com.yxm.product.common.DecreaseStockInput;
import com.yxm.product.server.dao.ProductInfoRepository;
import com.yxm.product.server.entity.ProductInfo;
import com.yxm.product.server.enums.ProductStatusEnum;
import com.yxm.product.server.enums.ResultEnum;
import com.yxm.product.server.exception.ProductException;
import com.yxm.product.server.service.ProductService;
import com.yxm.product.server.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
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

    @Autowired
    private AmqpTemplate amqpTemplate;

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
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        /**
         * 遍历:查看是否存在
         */
        for (DecreaseStockInput cartDTO:decreaseStockInputList){
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
            //发送mq消息
            amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfo));
        }
    }
}


































