package com.yxm.oder.server.repository;
import com.yxm.oder.server.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author yxm
 * @date 2019/4/18 0:37:37
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

}
