package com.ningyang.os.wechat.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/***
 * 微信验证
 */
public class WechatSignture {
    // 与接口配置信息中的Token要一�?
    public static String token = "sanxiang";

    /**
     * 确认请求来自微信服务器
     *
     * @param request
     * @param response
     */
    public static void doWechatSignture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature") == null ? "" : request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp") == null ? "" : request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce") == null ? "" : request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr") == null ? "" : request.getParameter("echostr");
        System.out.println(23456);
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(token, signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }


}
