package com.yxm.oder.server.repository;
import com.yxm.oder.server.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yxm
 * @date 2019/4/18 0:37:37
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
     List<OrderDetail>  findByOrderId(String orderId);
}
