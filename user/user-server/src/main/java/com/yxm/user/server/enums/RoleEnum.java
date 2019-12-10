package com.yxm.user.server.enums;

public enum RoleEnum {
    BUYER(1,"买家"),
    SELLER(2,"卖家");
    private int code;
    private String message;

    RoleEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
