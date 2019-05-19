package com.yxm.product.server.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author yxm
 * @date 2019/4/14 22:50:50
 */
public class ProductVO implements Serializable {

    @JsonProperty("name")//返回给前端的是 name 字段
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVoList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoVO> getProductInfoVoList() {
        return productInfoVoList;
    }

    public void setProductInfoVoList(List<ProductInfoVO> productInfoVoList) {
        this.productInfoVoList = productInfoVoList;
    }
}
