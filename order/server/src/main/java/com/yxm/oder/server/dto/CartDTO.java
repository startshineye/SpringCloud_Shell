package com.yxm.oder.server.dto;

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

  public CartDTO(){

  }
  public CartDTO(String productId,Integer productQuantity){
      this.productId = productId;
      this.productQuantity = productQuantity;
  }

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























