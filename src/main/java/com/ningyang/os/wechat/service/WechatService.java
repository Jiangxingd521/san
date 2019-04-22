/**
 * @Title: WechatService.java
 * @Package com.leesche.zcsp.node.pay.wecha.service
 * @Description:()
 * @author dennies yang
 * @date 2016年10月9日 下午7:26:59
 * @version V1.0
 */
package com.ningyang.os.wechat.service;

import com.ningyang.os.wechat.data.AccessToken;
import com.ningyang.os.wechat.data.Message;
import com.ningyang.os.wechat.data.Oauth2To;
import com.ningyang.os.wechat.data.WechatJsToken;

/**
 * @author dennies yang
 * @version V1.0
 * @Title: WechatService.java
 * @Package com.leesche.zcsp.node.pay.wecha.service
 * @Description:()
 * @date 2016年10月9日 下午7:26:59
 */
public interface WechatService {

    /***
     *
     * @Title: getOauth
     * @Description: (通过网页授权机制传入code获取用户信息)
     * @param @param code
     * @param @return
     * @return Message<Oauth2To>
     * @throws
     */
    Message<Oauth2To> getOauth(String code);

    /***
     *
     * @Title: getWechatRedirectUrl
     * @Description: (获取页面跳转界面)
     * @param @param url
     * @param @return
     * @return String
     * @throws
     */
    String getWechatRedirectUrl(String url);


    /***
     *
     * @Title: getWechatAccessToken
     * @Description: (获取token)
     * @param @return
     * @return Message<AccessToken>
     * @throws
     */
    Message<AccessToken> getWechatAccessToken();

    Message<AccessToken> getWechatAccessToken(String appId, String appSecret);

    /***
     *
     * @Title: getWechatJsToken
     * @Description: (获取JSToken)
     * @param @param accessToken
     * @param @return
     * @return Message<WechatJsToken>
     * @throws
     */
    Message<WechatJsToken> getWechatJsToken(String accessToken);

    /***
     *
     * @Title: refreshWechatAccessToken
     * @Description: (刷新微信ACCESSTOKEN)
     * @param @return
     * @return Message<AccessToken>
     * @throws
     */
    Message<AccessToken> refreshWechatAccessToken();

    /***
     *
     * @Title: refreshWechatJsToken
     * @Description: (刷新微信JSTOKEN)
     * @param @return
     * @return Message<AccessToken>
     * @throws
     */
    Message<WechatJsToken> refreshWechatJsToken();

    /**
     * 获取微信用户信息
     *
     * @param ACCESS_TOKEN
     * @param OPENID
     * @return
     */
    String getWXUserInfo(String ACCESS_TOKEN, String OPENID);


}
