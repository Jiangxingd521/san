package com.ningyang.os.wechat.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 淇′换绠＄悊鍣�
 *
 * @author liufeng
 * @date 2013-04-10
 */
public class MyX509TrustManager implements X509TrustManager {

    // 妫�煡瀹㈡埛绔瘉涔�
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    // 妫�煡鏈嶅姟鍣ㄧ璇佷功
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    // 杩斿洖鍙椾俊浠荤殑X509璇佷功鏁扮粍
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
