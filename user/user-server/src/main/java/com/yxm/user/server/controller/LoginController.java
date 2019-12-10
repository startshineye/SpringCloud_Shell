package com.yxm.user.server.controller;

import antlr.StringUtils;
import com.yxm.user.server.constant.CookieConstant;
import com.yxm.user.server.constant.RedisConstant;
import com.yxm.user.server.dataobject.UserInfo;
import com.yxm.user.server.enums.ResultEnum;
import com.yxm.user.server.enums.RoleEnum;
import com.yxm.user.server.service.UserService;
import com.yxm.user.server.utils.CookieUtil;
import com.yxm.user.server.utils.ResultVOUtil;
import com.yxm.user.server.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletRequest request,
                           HttpServletResponse response){
        /**
         * 卖家登录
         * cookie里设置token=UUID, redis设置key=UUID, value=xyz`
         */
        //0.判断是否已登录
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
         if(cookie != null &&
                 !org.apache.commons.lang.StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN,cookie.getValue()))))
                 {
                     return ResultVOUtil.success();
        }
        //1.根据openid查询
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2.判断角色
        if(userInfo.getRole() != RoleEnum.SELLER.getCode()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //3.设置redis
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN,token),openid,expire, TimeUnit.SECONDS);

        //4.设置cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,CookieConstant.expire);
        return ResultVOUtil.success();
    }
}
