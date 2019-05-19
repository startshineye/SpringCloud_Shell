package com.yxm.product.server.dao;
import com.yxm.product.server.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    /**
     * 2.获取类目type列表
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
