package com.ningyang.os.wechat.data;

public class ＷechatConfig {
    public static final String appid = "wxe6e07a2c36dcd25a";
    //	public static final String appid = "wx3606cfc594074349";//test
    public static final String appsecret = "ae7fabdf943fe82f1f62070abf48fce7";
//	public static final String appsecret = "35628f4c5f9393b62df7f2f5d7513429";//test

    public static final String partner = "1264701701";
    public static final String partnerkey = "LB73AQ1MJ59HSCIGRKZ6UWTVO0D2PFYN";


    public static final String backUri = "http://m.leesche.cn/node/topay";
    public static final String notify_url = "http://youyicloud.net/node/pay/notify";//http://m.leesche.cn/node/notify
    public static final String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String authorize = "https://open.weixin.qq.com/connect/oauth2/authorize?";
    public static final String WECHAT_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect ";
    public static final String accessToken = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String bizpayurl = "weixin://wxpay/bizpayurl";
    public static final String wechatTalkToken = "ed5c4290c00e4cf0852ef775c964f7c0";
    public static final String wechatTalkEncodingAESKey = "uN3tKauvYZOk2pXcLoHdUCsdlgKCRq94sjb3eKtrUgW";

    public static final String giveMoney = "http://m.leesche.cn/node/giveMoney";
    public static final String transfers = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    //获取微信用户信息
    public static final String WX_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    // 菜单创建（POST） 限100（次/天）
    public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";


    //提款证书路径
    public static final String giveMoneyURL = "/data/apiclient_cert.p12";
    //public static final String giveMoneyURL ="d:\\apiclient_cert.p12";
    //网页授权获取OpenID
    public static final String WECHAT_OAUTH = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code ";
    //网页授权界面
    public static final String WECHAT_OAUTHVIEW = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

    //微信同意下单界面
    public static final String WECHAT_UNIFIEORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //微信回调接口
    public static final String WECHAT_NOTIFY_URL = "http://youyicloud.net/node/pay/notify";//http://m.leesche.cn/node/payinfo/notify

    //微信取现 NO_CHECK：不校验真实姓名
    public static final String WECHAT_TRANSFERS_NO_CHECK = "NO_CHECK";

    //微信取现强校验真实姓名（未实名认证的用户会校验失败，无法转账）
    public static final String WECHAT_TRANSFERS_FORCE_CHECK = "FORCE_CHECK";

    //针对已实名认证的用户才校验真实姓名（未实名认证用户不校验，可以转账成功）
    public static final String WECHAT_TRANSFERS_OPTION_CHECK = "OPTION_CHECK";

    public static final Integer PAY_WECHAT = 2;

    public static final Integer PAY_ALIPAY = 3;


    public static final String WECHAT_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static final String WECHAT_JS_TOKEN = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    public static final String GIZWITSZAPI = "noti.gizwitsapi.com";


    public static final int OVERTIME = 30 * 1000;

    /**
     * ---------------------------------------支付宝授权相关配置-------------------------------------------------
     */
    public static final String ALIPAY_APPID = "2016092401964622";
    public static final String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";
    public static final String ALIPAY_SIGN_TYPE = "RSA";
    public static final String ALIPAY_GRANT_TYPE = "authorization_code";
    public static final String ALIPAY_AUTHVIEW = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=APPID&scope=auth_base&redirect_uri=ENCODED_URL";
    public static final String ALIPAY_APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFSRBTQXbzViA/Kpy2ndcqjjWVynat05atNnWslX8/Ldz0qUP72qRMgNlMEQJR9VuV5Rrxevb/pVhpYFBLnIskAWgx8jMHU50vBM/fOkFUAztiX6bAu5oNeH/i1hDb7uBVG+duOm/2WOmWkSra2x5A7vZiVFGsjj2VatSgvJQNDv1IkdOaLwsCbIWG/RJPfB/XyrpkD8s0rFCCsRX0EpoKBzU6UW9lZ0ByuR/81t3QvtW9MB61VLwkOSaOdK3xAgXEuJlLENNcDrDfsEK2r+f8cdwcLXZqFWFw++2apH5cVd61tnZZC7w7fLgQhiLyUEgbsARPSNhsJCqKPOshAwpzAgMBAAECggEAdRWo/Koed0aqTzL8tcNUd7KzSeGwfaTI0WJs3u34+SQt3NMwwHbiK75opNSwSBUUnknDcGxHvG2gi/kqsDFVXYzK3k1YHFCMXPiKLxu6tbWdHh7OmrJycvDuzvKjnsBqWvmHRSKapjqXAEiH7/fazMnesKRHlyDg4ngVTxo5qHm1iOmJ8UqS1QOOc7MoOBH9gnb5yR2aJHdbMhpkPk24+85SOccumOaoP0HmAQo2TqoKIAmXaiCQQj2Mc6qRoFQF2gXDd7mlnojcfGKxKJKr78SpDXHFQ3juADeLqOsV/lLs5S9Zhi0VFh4juH0sYNlVhuCPmBlYEYS2RMylRM77KQKBgQD6+kOWP0/IGOpNJ93xuu5XnKgbd+NLRF7OSTFont6AItctMpWsU2jdgyMqdSDQotBLfoi+5wfpgYIR1V3XlJSj5ZaR6D2yV9QI45v9zJcQV/Qeh8GMJzMTtSMoyV+Gmw3wEi+lDY3/uT1tgmnlrlQRsHCx0RwrVYmGjIfjgaAqxwKBgQCH89+DccCgrBttuMOWlLaGR7wnnh1yU7ubChy9xOU87DzZ3B99k8BQhukDWcaj+LCreFL4QLRXAV480n42aOyMOd+nzWpodegM0QsbStS4qjzy33AYpL7pD2pQUjlu+SP9GVF0Rt5Mo2Mp0NsBzxHhLNHp+Hw5/tL9aoAFQ4sW9QKBgA20uxoqbPX1fl4mTjWUSs3YDUoquuvyWOcVEjCvdGE1BeE/APN/PpvSPIpmCRu7uHpW96rNPR5gZzUIHO7X+2IT+KwRLcVejILLw0oR8TIAS6GbMlOwFFcALmJc1O2p7BHvPFDcM6QCfrtLQvM7GPY6V30ueUZYjoEdjZI8Ev4jAoGAKRFVImq5So/S1Ukr5A/WNyfyQXF06bU7hbD/T5/rs4SVOSZSaOe5/1lRiA6Vf7C/BX0aax14nJOwOOVI9PyrAAz3b8QQca+0QjLfjQYEzie2s5MlVHxnoxViAxNjgA9IfMsqHNrPig0NBnOtxJUdjIyyeF/0frxoVd7W/0ARAwUCgYAR0rTiKOl1t/aQWvYj5XtKm114PS5ktxCE1IYAPSqiv6EuP7vZdkWY/Z62vzKRDmtYmUITMlfLQdtq/Vnywk9JEMDw9qgWRYA2rSixUB6iVV9saAUMQBaJ7BkGR83pHSOnZ+FJSG1UzKHlerKj6RMpMAf8HHQx+dtGqqlQDlrPqg==";
    public static final String ALIPAY_APP_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhUkQU0F281YgPyqctp3XKo41lcp2rdOWrTZ1rJV/Py3c9KlD+9qkTIDZTBECUfVbleUa8Xr2/6VYaWBQS5yLJAFoMfIzB1OdLwTP3zpBVAM7Yl+mwLuaDXh/4tYQ2+7gVRvnbjpv9ljplpEq2tseQO72YlRRrI49lWrUoLyUDQ79SJHTmi8LAmyFhv0ST3wf18q6ZA/LNKxQgrEV9BKaCgc1OlFvZWdAcrkf/Nbd0L7VvTAetVS8JDkmjnSt8QIFxLiZSxDTXA6w37BCtq/n/HHcHC12ahVhcPvtmqR+XFXetbZ2WQu8O3y4EIYi8lBIG7AET0jYbCQqijzrIQMKcwIDAQAB";
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";


}
