/**
 * @Title: WechatTimer.java
 * @Package com.leesche.zcsp.node.pay.wecha.timer
 * @Description:()
 * @author dennies yang
 * @date 2016年10月12日 下午3:15:52
 * @version V1.0
 */
package com.ningyang.os.wechat.timer;

import com.ningyang.os.wechat.data.AccessToken;
import com.ningyang.os.wechat.data.Message;
import com.ningyang.os.wechat.data.WechatJsToken;
import com.ningyang.os.wechat.service.WechatService;
import com.ningyang.os.wechat.service.impl.WechatServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author dennies yang
 * @version V1.0
 * @Title: WechatTimer.java
 * @Package com.leesche.zcsp.node.pay.wecha.timer
 * @Description:(定时更新token)
 * @date 2016年10月12日 下午3:15:52
 */
@Component
@Lazy(value = false)
public class WechatTimer {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WechatServiceImpl.class);


    public static String ASSCEE_TOKEN = "";
    public static String JS_TIKET = "";
    @Autowired
    WechatService wechatService;


    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void doWechatTimer() {
        Message<AccessToken> am = wechatService.getWechatAccessToken();
        if (Message.isError(am.getType())) {
            LOGGER.error(am.toString());
        } else {
            ASSCEE_TOKEN = am.getContent().getAccess_token();
            if (StringUtils.isNotBlank(ASSCEE_TOKEN)) {
                Message<WechatJsToken> jsm = wechatService.getWechatJsToken(ASSCEE_TOKEN);
                if (Message.isError(jsm.getType())) {
                    LOGGER.error(am.toString());
                }
                JS_TIKET = jsm.getContent().getTicket();
            }
        }

        LOGGER.info("suyuan token === " + ASSCEE_TOKEN);
        LOGGER.info("suyuan JS_TIKET === " + JS_TIKET);
    }


}
