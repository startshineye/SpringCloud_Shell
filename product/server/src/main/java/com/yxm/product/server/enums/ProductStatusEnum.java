package com.yxm.product.server.enums;

/**
 * @author yxm
 * @date 2019/4/14 22:34:34
 */
public enum  ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String name;

    ProductStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
