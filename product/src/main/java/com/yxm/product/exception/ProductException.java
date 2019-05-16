package com.yxm.product.exception;

import com.yxm.product.enums.ResultEnum;

/**
 * @author yxm
 * @date 2019/5/12 15:41:41
 */
public class ProductException extends RuntimeException {
    private Integer code;

    public ProductException(Integer code,String message){
      super(message);
      this.code =code;
    }

    public ProductException(ResultEnum resultEnum){
           super(resultEnum.getMessage());
           this.code = resultEnum.getCode();
    }
}




































