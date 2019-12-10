package com.yxm.user.server.enums;
/**
 * @author yxm
 * @date 2019/5/12 15:45:45
 */
public enum ResultEnum {
    LOGIN_FAIL(1,"登陆失败"),
    ROLE_ERROR(2,"角色错误");
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
