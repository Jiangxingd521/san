/**
 * @Title: AccessToken.java
 * @Package com.leesche.zcsp.node.pay.wecha.data
 * @Description:()
 * @author dennies yang
 * @date 2016年10月12日 下午2:48:30
 * @version V1.0
 */
package com.ningyang.os.wechat.data;

import java.io.Serializable;

/**
 * @author dennies yang
 * @version V1.0
 * @Title: AccessToken.java
 * @Package com.leesche.zcsp.node.pay.wecha.data
 * @Description:()
 * @date 2016年10月12日 下午2:48:30
 */
public class AccessToken implements Serializable {
    private String access_token;
    private Integer expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }


}
