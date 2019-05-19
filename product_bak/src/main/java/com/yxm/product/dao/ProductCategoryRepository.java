package com.yxm.product.dao;
import com.yxm.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 * Created by zghgchao 2018/6/13 22:55
 */

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    /**
     * 2.获取类目type列表
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
