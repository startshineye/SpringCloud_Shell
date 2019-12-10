package com.yxm.user.server.vo;
import java.io.Serializable;
/**
 * @author yxm
 * @date 2019/4/14 22:42:42
 */
public class ResultVO<T> implements Serializable{
    /**
     * 错误码
     */
  private Integer code;
    /**
     * 提示信息
     */
  private String msg;
    /**
     * 具体内容
     */
  private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
