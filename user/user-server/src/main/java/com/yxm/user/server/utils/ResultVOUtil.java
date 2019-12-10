package com.yxm.user.server.utils;
import com.yxm.user.server.enums.ResultEnum;
import com.yxm.user.server.vo.ResultVO;
/**
 * @author yxm
 * @date 2019/4/17 23:28:28
 */
public class ResultVOUtil {

    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
       // resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.LOGIN_FAIL.getCode());
        resultVO.setMsg(ResultEnum.LOGIN_FAIL.getMessage());
        resultVO.setData(object);
        return resultVO;
    }
}
