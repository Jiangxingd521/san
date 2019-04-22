/**
 * @Title: WechatJsToken.java
 * @Package com.leesche.zcsp.node.pay.wecha.data
 * @Description:()
 * @author dennies yang
 * @date 2016年10月12日 下午2:50:55
 * @version V1.0
 */
package com.ningyang.os.wechat.data;

import java.io.Serializable;

/**
 * @author dennies yang
 * @version V1.0
 * @Title: WechatJsToken.java
 * @Package com.leesche.zcsp.node.pay.wecha.data
 * @Description:()
 * @date 2016年10月12日 下午2:50:55
 */
public class WechatJsToken implements Serializable {
    private Integer errcode;
    private String errmsg;
    private String ticket;
    private Integer expires_in;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }


}
