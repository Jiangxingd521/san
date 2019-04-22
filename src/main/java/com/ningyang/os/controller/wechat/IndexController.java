package com.ningyang.os.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ningyang.os.action.utils.BrowerUtils;
import com.ningyang.os.pojo.*;
import com.ningyang.os.service.*;
import com.ningyang.os.wechat.data.AccessToken;
import com.ningyang.os.wechat.data.Message;
import com.ningyang.os.wechat.data.Oauth2To;
import com.ningyang.os.wechat.service.WechatService;
import com.ningyang.os.wechat.utils.WechatSignture;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Controller
@RequestMapping("/wechat")
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    //商品信息
    @Autowired
    private ISerGoodsInfoService serGoodsInfoService;
    @Autowired
    private ISerBrandSeriesProductInfoService productInfoService;
    //布奖信息
    @Autowired
    private ISerPrizeRecodeInfoService serPrizeRecodeInfoService;
    //会员扫码记录
    @Autowired
    private IMemberScanningService iMemberScanningService;
    //会员信息
    @Autowired
    private IMemberInfoService iMemberInfoService;
    //微信
    @Autowired
    private WechatService wechatService;

    @Autowired
    private ISerPrizeSetInfoService prizeSetInfoService;

    @Autowired
    private ISerPrizeManagerInfoService iSerPrizeManagerInfoService;


    /***
     * 确认请求来自微信服务器
     * @return
     */
    @GetMapping("signture")
    public void getErpDatas(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        WechatSignture.doWechatSignture(request, response);
    }

    /***
     * 溯源主界面
     * @return
     */
    @GetMapping("/index/{comanyid}/{prcode}/{day}")
    public String suyuanIndexForPrize(
            @RequestHeader("User-Agent") String userAgent,
            final @PathVariable("comanyid") Integer comanyid,
            final @PathVariable("prcode") String prcode,
            final @PathVariable("day") Integer day,
            String source,
            String code,
            HttpServletRequest request,
            HttpServletResponse reponse,
            HttpSession session,
            ModelMap model
    ) {
        //判断是否是微信浏览器
        boolean isWechatBrower = BrowerUtils.isWechatBrower(userAgent);
        session.setAttribute("source", source);
        String openid = "";
        // 如果微信浏览器则使用微信网页授权模型
        String url = "http://" + request.getServerName() +/*":"+request.getServerPort()+*/ request.getContextPath() + "/wechat/prize";
        if (isWechatBrower && StringUtils.isEmpty(code)) {
            System.out.println(url.toString());
            System.out.println(wechatService.getWechatRedirectUrl(url.toString()));
            return "redirect:" + wechatService.getWechatRedirectUrl(url.toString());
        } else {
            return "redirect:" + url;
        }
    }

    /***
     * 溯源主界面 抽奖界面
     * @return
     */
    @GetMapping("prize")
    public String suyuanPrize(
            @RequestHeader("User-Agent") String userAgent,
            String code,
            Long prizeRecodeId,
            HttpServletRequest request,
            HttpServletResponse reponse,
            HttpSession session,
            ModelMap model
    ) {
        //,@SessionAttribute("source")String source ,@SessionAttribute("openid") String openid ,
        String source = session.getAttribute("source") == null ? "" : session.getAttribute("source").toString();
        String openid = session.getAttribute("openid") == null ? "" : session.getAttribute("openid").toString();
        //判断是否是微信浏览器
        boolean isWechatBrower = BrowerUtils.isWechatBrower(userAgent);
        if (StringUtils.isNotEmpty(code)) {
            Message<Oauth2To> message = wechatService.getOauth(code);
            if (message.getType() == Message.Type.error) {
                LOGGER.error("读取用户信息失败......");
            } else if (message.getType() == Message.Type.success) {
                openid = message.getContent().getOpenid();
                session.setAttribute("openid", openid);
                MemberInfo memberInfo = iMemberInfoService.getOne(new QueryWrapper<MemberInfo>()
                        .eq("open_id", message.getContent().getOpenid()));
                if (memberInfo == null) {
                    memberInfo = this.getUserInfo(message.getContent().getOpenid());
                    if (memberInfo != null) {
                        System.out.println(memberInfo.getOpenId());
                        memberInfo.setCreateTime(new Date());
                        memberInfo.setUpdateTime(new Date());
                        iMemberInfoService.save(memberInfo);
                    }
                }

            }
        }
        SerGoodsInfo goodsInfo = serGoodsInfoService.getOne(new QueryWrapper<SerGoodsInfo>().eq("m1", source));

        /**抽奖*/
        if (/*StringUtils.isNotEmpty(openid)&&*/prizeRecodeId != null) {
            SerPrizeRecodeInfo prizeRecodeInfo = serPrizeRecodeInfoService.getById(prizeRecodeId);
            prizeRecodeInfo.setPrizeRecodeId(prizeRecodeId);
            prizeRecodeInfo.setCashTime(new Date());
            prizeRecodeInfo.setOpenId(openid);
            serPrizeRecodeInfoService.updateById(prizeRecodeInfo);
            SerGoodsInfo updateGoodsInfo=goodsInfo;
            updateGoodsInfo.setGoodsState(3);//设置产品失效
            serGoodsInfoService.updateById(updateGoodsInfo);

        }

        //读取读取商品信息  获取产品状态
        //读取产品数据
        //读取布奖记录
        SerPrizeRecodeInfo prizeRecodeInfo = serPrizeRecodeInfoService.getOne(new QueryWrapper<SerPrizeRecodeInfo>()
                .eq("product_code", source));

        //读取设定类型
        SerPrizeManagerInfo serPrizeManagerInfo = null;
        SerPrizeSetInfo serPrizeSetInfo = null;
        if (prizeRecodeInfo != null) {
            serPrizeSetInfo = prizeSetInfoService.getById(prizeRecodeInfo.getPrizeSetId());
            if (serPrizeSetInfo != null) {
                serPrizeManagerInfo = iSerPrizeManagerInfoService.getById(serPrizeSetInfo.getPrizeManagerId());
            }
        }
        boolean isHB = false;//是否可领取红包
        boolean isPT = false;//是否可领取积分
        if (goodsInfo != null && goodsInfo.getGoodsState() == 2 && prizeRecodeInfo != null && prizeRecodeInfo.getPrizeState() == 1) {
            if (prizeRecodeInfo.getSdata1() != null & prizeRecodeInfo.getSdata1().equals("HB")) {
                isHB = true;
            } else if (prizeRecodeInfo.getSdata1() != null & prizeRecodeInfo.getSdata1().equals("PT")) {
                isPT = true;//是否可领取积分
            }
        }

        //获取IP地址
        String ip = this.getIpAddr(request);
        //记录扫码次数

        MemberScanning memberScanning = iMemberScanningService.getOne(new QueryWrapper<MemberScanning>()
                .eq("sdata1", ip)
                .eq("pr_code", source)
                .eq("open_id", openid));

        if (memberScanning == null) {
            memberScanning = new MemberScanning();
            memberScanning.setOpenId(openid);
            memberScanning.setProductCode(source);
            memberScanning.setSdata1(ip);
            memberScanning.setCreateTime(new Date());
            iMemberScanningService.save(memberScanning);
        }

        model.addAttribute("isHB", isHB);
        model.addAttribute("isPT", isPT);
        model.addAttribute("prizeRecodeInfo", prizeRecodeInfo);
        model.addAttribute("serPrizeSetInfo", serPrizeSetInfo);
        model.addAttribute("goodsInfo", goodsInfo);

        return "wechat/index";
    }


    /***
     * 产品详情
     * @return
     */
    @GetMapping("details")
    public String suyuanProdDetails(
            @RequestHeader("User-Agent") String userAgent,
            @SessionAttribute("source") String source,
            Long goodid,
            ModelMap model
    ) {
        //读取读取商品信息  获取产品状态
        SerGoodsInfo goodsInfo = serGoodsInfoService.getById(goodid);
        //d读取产品信息
        SerBrandSeriesProductInfo productInfo = productInfoService.getById(goodsInfo.getBrandSeriesProductId());
        //读取扫码次数
        Integer scanns = iMemberScanningService.count(new QueryWrapper<MemberScanning>().eq("product_code", goodsInfo.getM1()));
        //读取扫码时间
        MemberScanning memberScanning = iMemberScanningService.getOne(new QueryWrapper<MemberScanning>().eq("product_code", goodsInfo.getM1()));
        //读取布奖记录
        SerPrizeRecodeInfo prizeRecodeInfo = serPrizeRecodeInfoService.getOne(new QueryWrapper<SerPrizeRecodeInfo>().eq("product_code", source));
        //读取设定类型
        SerPrizeManagerInfo serPrizeManagerInfo = null;
        SerPrizeSetInfo serPrizeSetInfo = null;
        if (prizeRecodeInfo != null) {
            serPrizeSetInfo = prizeSetInfoService.getById(prizeRecodeInfo.getPrizeSetId());
            if (serPrizeSetInfo != null) {
                serPrizeManagerInfo = iSerPrizeManagerInfoService.getById(serPrizeSetInfo.getPrizeManagerId());
            }
        }

        model.addAttribute("serPrizeSetInfo", serPrizeSetInfo);
        model.addAttribute("scanns", scanns);
        model.addAttribute("goodsInfo", goodsInfo);
        model.addAttribute("productInfo", productInfo);
        model.addAttribute("memberScanning", memberScanning);

        return "wechat/details";
    }


    /***
     * 活动规则
     * @return
     */
    @GetMapping("activity")
    public String suyuanPrizeRule(
            @RequestHeader("User-Agent") String userAgent,
            @SessionAttribute("source") String source,
            Long prizeSetId,
            Long goodid,
            ModelMap model
    ) {
        //读取规则信息
        SerPrizeSetInfo serPrizeSetInfo = prizeSetInfoService.getById(prizeSetId);
        String content = "";
        if (serPrizeSetInfo != null) {
            SerPrizeManagerInfo serPrizeManagerInfo = iSerPrizeManagerInfoService.getById(serPrizeSetInfo.getPrizeManagerId());
            content = serPrizeManagerInfo.getPrizeContent();
        }
        model.addAttribute("content", content);
        model.addAttribute("goodid", goodid);

        return "wechat/activity";
    }

    private MemberInfo getUserInfo(String openId) {
        MemberInfo wxUserInfo = null;
        try {
            Message<AccessToken> messageAccess = wechatService.getWechatAccessToken();
            String AccessToken = messageAccess.getContent().getAccess_token();
            String userInfoStr = wechatService.getWXUserInfo(AccessToken, openId);
            wxUserInfo = JSON.parseObject(userInfoStr, MemberInfo.class);
            wxUserInfo.setOpenId(openId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxUserInfo;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

}
