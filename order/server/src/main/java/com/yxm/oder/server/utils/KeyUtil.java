package com.yxm.oder.server.utils;

import java.util.Random;

/**
 * @author yxm
 * @date 2019/4/18 23:59:59
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized  String  genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
