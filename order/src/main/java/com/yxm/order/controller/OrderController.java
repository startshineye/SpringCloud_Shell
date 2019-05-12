package com.yxm.order.controller;
import com.yxm.order.converter.OrderForm2OrderDTOConverter;
import com.yxm.order.dto.OrderDTO;
import com.yxm.order.enums.ResultEnum;
import com.yxm.order.exception.OrderException;
import com.yxm.order.form.OrderForm;
import com.yxm.order.service.OrderService;
import com.yxm.order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yxm
 * @date 2019/4/18 0:52:52
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1.参数校验
     * 2.查询商品信息(调用商品服务)
     * 3.计算总价
     * 4.扣库存(调用商品服务)
     * 5.订单入库
     */
    @PostMapping("/create")
    private Object create(@Valid OrderForm orderForm,
                          BindingResult bindingResult){
        //检验不通过抛出自定义异常
        if (bindingResult.hasErrors()){
            //https://www.cnblogs.com/weiapro/p/7633645.html
           log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //orderForm->orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }
}
