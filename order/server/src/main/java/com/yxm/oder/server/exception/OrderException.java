package com.yxm.oder.server.exception;

import com.yxm.oder.server.enums.ResultEnum;

/**
 * @author yxm
 * @date 2019/4/19 0:43:43
 */
public class OrderException extends RuntimeException {
    private Integer code;
    public OrderException(Integer code, String message){
      super(message);
      this.code = code;
    }
    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
