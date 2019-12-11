package com.yxm.oder.server.repository;
import com.yxm.oder.server.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author yxm
 * @date 2019/4/18 0:38:38
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

}
