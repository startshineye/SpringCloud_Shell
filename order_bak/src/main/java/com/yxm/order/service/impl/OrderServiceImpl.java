package com.yxm.order.service.impl;
import com.yxm.order.client.ProductClient;
import com.yxm.order.dto.CartDTO;
import com.yxm.order.dto.OrderDTO;
import com.yxm.order.dto.ProductInfo;
import com.yxm.order.entity.OrderDetail;
import com.yxm.order.entity.OrderMaster;
import com.yxm.order.enums.OrderStatusEnum;
import com.yxm.order.enums.PayStatusEnum;
import com.yxm.order.repository.OrderDetailRepository;
import com.yxm.order.repository.OrderMasterRepository;
import com.yxm.order.service.OrderService;
import com.yxm.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yxm
 * @date 2019/4/18 23:42:42
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductClient productClient;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        //1.TODO 查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
        //2.TODO 计算订单总价
        //定义总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            //总价=(单价*数量)+总价
            Integer productQuantity = orderDetail.getProductQuantity();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getProductId().equals(orderDetail.getProductId())){
                    orderAmout = productInfo.getProductPrice().multiply(new BigDecimal(productQuantity)).add(orderAmout);

                   //订单详情赋值
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());

                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }

        }
        //3.TODO 扣除库存(调用商品服务)
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //4. 订单入库
        OrderMaster orderMaster = new OrderMaster();
        //先设置主键 会copy到orderMaster
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
