package com.yxm.product.server.utils;

import com.yxm.product.server.vo.ResultVO;

/**
 * @author yxm
 * @date 2019/4/17 23:28:28
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(object);
        return resultVO;
    }
}
