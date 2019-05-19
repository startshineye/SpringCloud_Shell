package com.yxm.product.enums;

/**
 * @author yxm
 * @date 2019/5/12 15:45:45
 */
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1,"商品不存在"),
    PRODUCT_STOCK_ERROE(2,"商品库存错误" );
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
