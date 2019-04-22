package com.ningyang.os.action.utils;

/**
 * 各种浏览器验证 包含支付宝 微信
 *
 * @Author dennies yang
 */
public class BrowerUtils {

    /**
     * 判断是否是微信浏览器
     *
     * @param userAgent
     * @return
     */
    public static boolean isWechatBrower(String userAgent) {
        String lowerCase = userAgent.toLowerCase();
        return lowerCase.indexOf("micromessenger") >= 0;
    }
}
