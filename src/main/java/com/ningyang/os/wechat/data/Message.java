package com.ningyang.os.wechat.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/***
 *
 * @Title: Message.java
 * @Package com.leesche.zcsp.node.pay.wecha.data
 * @Description:(&#x5fae;&#x4fe1;&#x4fe1;&#x606f;&#x8fd4;&#x56de;&#x7ed3;&#x679c;&#x503c;)
 * @author dennies yang
 * @date 2016&#x5e74;10&#x6708;10&#x65e5; &#x4e0b;&#x5348;12:37:07
 * @version V1.0
 */
public class Message<T> {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String WARN = "warn";
    private Type type;
    private T content;
    private WechatError wechatError;

    public Message() {
    }

    public Message(Type type, T content) {
        this.type = type;
        this.content = content;

    }

    public static Message wechatMessage(String text, Class<?> c) {
        JSONObject messageObject = JSON.parseObject(text);
        if (StringUtils.isBlank(text)) {
            return Message.error(new WechatError(-2, "字符串解析为空"));
        }
        if (messageObject.containsKey("errcode") && (messageObject.getInteger("errcode") != 0)) {
            return Message.error(JSON.parseObject(text, WechatError.class));
        } else {

            return Message.success(JSON.parseObject(text, c), null);
        }

    }

    public static Message smsMeassge(String text, Class<?> c) {
        JSONObject messageObject = JSON.parseObject(text);
        if (StringUtils.isBlank(text)) {
            return Message.error(new WechatError(-2, "字符串解析为空"));
        }
        if (messageObject.containsKey("code") && (messageObject.getInteger("code") != 0)) {
            return Message.error(JSON.parseObject(text, c), null);
        } else {

            return Message.success(JSON.parseObject(text, c), null);
        }


    }


    public Message(Type type, WechatError wechatError) {
        this.type = type;
        this.wechatError = wechatError;
    }

    public Message(Type type, T content, Object[] args) {
        this.type = type;
        this.content = content;
    }

    public static Message success(Object content, Object[] args) {
        return new Message(Type.success, content, args);
    }

    public static Message warn(Object content, Object[] args) {
        return new Message(Type.warn, content, args);
    }

    public static Message error(Object content, Object[] args) {
        return new Message(Type.error, content, args);
    }

    public static Message error(WechatError wechatError) {
        return new Message(Type.error, wechatError);
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public WechatError getWechatError() {
        return wechatError;
    }

    public void setWechatError(WechatError wechatError) {
        this.wechatError = wechatError;
    }

    public static boolean isError(Message.Type type) {
        return Message.Type.error == type;
    }

    public static boolean isSuccess(Message.Type type) {
        return Message.Type.success == type;
    }

    public enum Type {
        success,

        warn,

        error
    }
}
