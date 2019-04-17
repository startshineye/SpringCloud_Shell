package com.yxm.order.repository;
import com.yxm.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author yxm
 * @date 2019/4/18 0:37:37
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

}
