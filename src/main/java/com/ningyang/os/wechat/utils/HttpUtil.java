package com.ningyang.os.wechat.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.protocol.DefaultProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


@SuppressWarnings("deprecation")
public class HttpUtil {
    public static String post(String url, String body, Map<String, String> headers) {
        PostMethod post = new PostMethod(url);
        post.setRequestBody(body);// 鏉╂瑩鍣峰ǎ璇插xml鐎涙顑佹稉锟�
        return method(post, headers);
    }

    public static String get(String url, Map<String, String> headers) {
        GetMethod get = new GetMethod(url);
        return method(get, headers);
    }

    public static String delete(String url, Map<String, String> headers) {
        DeleteMethod delete = new DeleteMethod(url);
        return method(delete, headers);
    }

    public static String put(String url, Map<String, String> headers) {
        return put(url, null, headers);
    }

    public static String put(String url, String body, Map<String, String> headers) {
        PutMethod put = new PutMethod(url);
        put.setRequestBody(body);
        return method(put, headers);
    }

    private static String method(HttpMethod method, Map<String, String> headers) {
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));

        HttpClient httpclient = new HttpClient();// 閸掓稑缂�HttpClient 閻ㄥ嫬鐤勬笟锟�
        String res = null;
        try {
            if (headers != null) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    method.setRequestHeader(entry.getKey(), entry.getValue());
                }
            }
            httpclient.executeMethod(method);

            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            res = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        method.releaseConnection();// 闁插﹥鏂佹潻鐐村复
        return res;
    }

    public static Map<String, String> getPostHeaders() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Connection", "keep-alive");
        map.put("Accept", "*/*");
        map.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        map.put("Host", "api.mch.weixin.qq.com");
        map.put("X-Requested-With", "XMLHttpRequest");
        map.put("Cache-Control", "max-age=0");
        map.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        return map;
    }

    public static String get2(String url, Map<String, String> headers) {
        GetMethod get = new GetMethod(url);
        return method2(get, headers);
    }


    public static String post2(String url, String body, Map<String, String> headers) {
        PostMethod post = new PostMethod(url);
        post.setRequestBody(body);// 鏉╂瑩鍣峰ǎ璇插xml鐎涙顑佹稉锟�
        return method2(post, headers);
    }

    private static String method2(HttpMethod method, Map<String, String> headers) {
        ProtocolSocketFactory fcty = new DefaultProtocolSocketFactory();
        Protocol.registerProtocol("http", new Protocol("http", fcty, 80));

        HttpClient httpclient = new HttpClient();// 閸掓稑缂�HttpClient 閻ㄥ嫬鐤勬笟锟�
        String res = null;
        try {
            if (headers != null) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    method.setRequestHeader(entry.getKey(), entry.getValue());
                }
            }
            httpclient.executeMethod(method);

            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            res = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        method.releaseConnection();// 闁插﹥鏂佹潻鐐村复
        return res;
    }
}
