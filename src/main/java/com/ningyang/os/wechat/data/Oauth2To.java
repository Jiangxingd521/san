/**
 * @Title: Oauth2To.java
 * @Package com.leesche.zcsp.node.pay.wecha.data
 * @Description:()
 * @author dennies yang
 * @date 2016年10月9日 下午7:32:16
 * @version V1.0
 */
package com.ningyang.os.wechat.data;

/**
 * @author dennies yang
 * @version V1.0
 * @Title: Oauth2To.java
 * @Package com.leesche.zcsp.node.pay.wecha.data
 * @Description:()
 * @date 2016年10月9日 下午7:32:16
 */
public class Oauth2To {
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private String openid;
    private String scope;

    public Oauth2To() {
        super();
        //Auto-generated constructor stub
    }

    public Oauth2To(String access_token, Integer expires_in, String refresh_token, String openid, String scope) {
        super();
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.openid = openid;
        this.scope = scope;
    }

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

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


}
