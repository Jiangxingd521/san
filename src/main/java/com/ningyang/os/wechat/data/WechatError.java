/**
 * @Title: WechatError.java
 * @Package com.leesche.zcsp.node.pay.wecha.data
 * @Description:()
 * @author dennies yang
 * @date 2016年10月10日 上午11:58:25
 * @version V1.0
 */
package com.ningyang.os.wechat.data;

import java.io.Serializable;

/**
 * @author dennies yang
 * @version V1.0
 * @Title: WechatError.java
 * @Package com.leesche.zcsp.node.pay.wecha.data
 * @Description:()
 * @date 2016年10月10日 上午11:58:25
 */
public class WechatError implements Serializable {
    //微信错误代码
    private int errcode;
    //微信错误信息
    private String errmsg;

    public WechatError() {
    }

    public WechatError(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "微信错误代码" + errcode + ",微信错误信息:" + errmsg;
    }


}
