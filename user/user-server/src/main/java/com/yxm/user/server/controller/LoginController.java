package com.yxm.user.server.controller;

import com.yxm.user.server.constant.CookieConstant;
import com.yxm.user.server.dataobject.UserInfo;
import com.yxm.user.server.enums.ResultEnum;
import com.yxm.user.server.enums.RoleEnum;
import com.yxm.user.server.service.UserService;
import com.yxm.user.server.utils.CookieUtil;
import com.yxm.user.server.utils.ResultVOUtil;
import com.yxm.user.server.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response){
        /**
         * 买家登录
         * 1.返回cookie里面设置openid=abc
         */
        //1.根据openid查询
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null){
          return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2.判断角色
        if(userInfo.getRole() != RoleEnum.BUYER.getCode()){
             return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        //3.设置cookie
        CookieUtil.set(response, CookieConstant.OPENID,openid,CookieConstant.expire);
         return ResultVOUtil.success();
    }
}
