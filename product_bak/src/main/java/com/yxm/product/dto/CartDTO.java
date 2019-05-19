package com.yxm.product.dto;

/**
 * @author yxm
 * @date 2019/5/12 15:26:26
 */
public class CartDTO {
    /**
     * 商品id
     */
  private String productId;
    /**
     * 商品数量
     */
  private Integer productQuantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}























