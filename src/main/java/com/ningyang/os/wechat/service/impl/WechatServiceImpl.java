/**
 * @Title: WechatServiceImpl.java
 * @Package com.leesche.zcsp.node.pay.wecha.service.encha.impl
 * @Description:()
 * @author dennies yang
 * @date 2016年10月9日 下午7:35:38
 * @version V1.0
 */
package com.ningyang.os.wechat.service.impl;

import com.ningyang.os.wechat.data.*;
import com.ningyang.os.wechat.service.WechatService;
import com.ningyang.os.wechat.timer.WechatTimer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static cn.hutool.http.HttpUtil.get;


/**
 * @author dennies yang
 * @version V1.0
 * @Title: WechatServiceImpl.java
 * @Package com.leesche.zcsp.node.pay.wecha.service.encha.impl
 * @Description:()
 * @date 2016年10月9日 下午7:35:38
 */
@SuppressWarnings("all")
@Service("wechatService")
public class WechatServiceImpl implements WechatService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WechatServiceImpl.class);


    @Override
    public Message<Oauth2To> getOauth(String code) {
        String Oauth2Url = ＷechatConfig.WECHAT_OAUTH.replace("APPID", ＷechatConfig.appid)
                .replace("SECRET", ＷechatConfig.appsecret).replace("CODE", code);
        System.out.println("Oauth2Url:" + Oauth2Url);

        String result = get(Oauth2Url);

        LOGGER.error(result);
        System.out.println("result:" + result);
        return Message.wechatMessage(result, Oauth2To.class);
    }

    @Override
    public String getWechatRedirectUrl(String url) {
        return ＷechatConfig.WECHAT_OAUTHVIEW.replace("APPID", ＷechatConfig.appid).replace("REDIRECT_URI", url);
    }


    @Override
    public Message<AccessToken> getWechatAccessToken() {
        String accessTokenURL = ＷechatConfig.WECHAT_ACCESS_TOKEN.replace("APPID", ＷechatConfig.appid).replaceAll("APPSECRET", ＷechatConfig.appsecret);
        String result = get(accessTokenURL);
        System.out.println("code====" + result);
        LOGGER.error(result);
        return Message.wechatMessage(result, AccessToken.class);
    }

    @Override
    public Message<WechatJsToken> getWechatJsToken(String accessToken) {
        String jsTokenURL = ＷechatConfig.WECHAT_JS_TOKEN.replace("ACCESS_TOKEN", accessToken);
        String result = get(jsTokenURL);
        LOGGER.error(result);
        return Message.wechatMessage(result, WechatJsToken.class);
    }

    @Override
    public Message<AccessToken> refreshWechatAccessToken() {
        Message<AccessToken> am = this.getWechatAccessToken();
        if (Message.isSuccess(am.getType())) {
            WechatTimer.ASSCEE_TOKEN = am.getContent().getAccess_token();
        } else if (Message.isError(am.getType())) {
            LOGGER.error(am.toString());
        }


        return am;
    }

    @Override
    public Message<WechatJsToken> refreshWechatJsToken() {
        if (StringUtils.isBlank(WechatTimer.ASSCEE_TOKEN)) {
            refreshWechatAccessToken();
        }
        Message<WechatJsToken> jm = this.getWechatJsToken(WechatTimer.ASSCEE_TOKEN);
        if (Message.isSuccess(jm.getType())) {
            WechatTimer.JS_TIKET = jm.getContent().getTicket();
        } else if (Message.isError(jm.getType())) {
            LOGGER.error(jm.toString());
        }
        return jm;
    }


    @Override
    public String getWXUserInfo(String ACCESS_TOKEN, String OPENID) {
        String WXUserInfoURL = ＷechatConfig.WX_USER_INFO.replace("ACCESS_TOKEN", ACCESS_TOKEN).replace("OPENID", OPENID);
        String result = get(WXUserInfoURL);
        return result;
    }



    /*	*/

    /**
     * 获取微信用户信息
     *
     * @param openId
     * @return
     *//*
	private WXUserInfo getUserInfo(String openId, String appId, String appSecret) {
		WXUserInfo wxUserInfo = null;
		try {
			Message<AccessToken> messageAccess = getWechatAccessToken(appId,appSecret);
			String AccessToken = messageAccess.getContent().getAccess_token();
			String userInfoStr = getWXUserInfo(AccessToken, openId);
			wxUserInfo = JSON.parseObject(userInfoStr, WXUserInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
			wxUserInfo = new WXUserInfo();
		}
		return wxUserInfo;
	}*/
    @Override
    public Message<AccessToken> getWechatAccessToken(String appId, String appSecret) {
        String accessTokenURL = ＷechatConfig.WECHAT_ACCESS_TOKEN.replace("APPID", appId).replaceAll("APPSECRET", appSecret);
        String result = get(accessTokenURL);
        LOGGER.error(result);
        return Message.wechatMessage(result, AccessToken.class);
    }

}
