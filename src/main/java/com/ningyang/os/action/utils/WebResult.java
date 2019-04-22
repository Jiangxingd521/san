package com.ningyang.os.action.utils;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by kaider on 2017/4/19.
 */
public class WebResult {

    private static final String DEFAULT_SUCCESS_INFO = "操作成功";
    private static final String TOTAL_KEY = "total";
    private static final String ROWS_KEY = "rows";

    private String resultKey = "result";
    private String infoKey = "info";

    private Boolean result;
    private String info;

    private final Map<String, Object> elements = new LinkedHashMap<String, Object>();

    private WebResult() {
    }

    private WebResult(Boolean result, String info) {
        this.result = result;
        this.info = info;
    }

    private WebResult(String resultKey, Boolean result, String infoKey, String info) {
        this.resultKey = resultKey;
        this.infoKey = infoKey;
        this.result = result;
        this.info = info;
    }

    /**
     * <p>success.</p>
     *
     * @return a {@link } object.
     */
    public static WebResult success() {
        return new WebResult(Boolean.TRUE, DEFAULT_SUCCESS_INFO);
    }

    /**
     * <p>success.</p>
     *
     * @param successKey a {@link String} object.
     * @param infoKey    a {@link String} object.
     * @return a {@link } object.
     */
    public static WebResult success(String successKey, String infoKey) {
        return new WebResult(successKey, Boolean.TRUE, infoKey, DEFAULT_SUCCESS_INFO);
    }

    /**
     * <p>failureListQuery.</p>
     *
     * @param throwable a {@link Throwable} object.
     * @return a {@link } object.
     */
    public static WebResult failureListQuery(Throwable throwable) {
        WebResult webResult = failure(throwable);
        webResult.putEmptyRowsAndItSizeIsTotal();
        return webResult;
    }

    /**
     * <p>failure.</p>
     *
     * @param throwable a {@link Throwable} object.
     * @return a {@link } object.
     */
    public static WebResult failure(Throwable throwable) {
        String msg = throwable.getLocalizedMessage();

        if (isBlank(msg)) {
            msg = "未知错误。" + throwable.toString();
        }

        return new WebResult(Boolean.FALSE, msg);
    }

    /**
     * <p>failure.</p>
     *
     * @param resultKey a {@link String} object.
     * @param infoKey   a {@link String} object.
     * @param throwable a {@link Throwable} object.
     * @return a {@link } object.
     */
    public static WebResult failure(String resultKey, String infoKey, Throwable throwable) {
        String msg = throwable.getLocalizedMessage();

        if (isBlank(msg)) {
            msg = "未知错误。" + throwable.getMessage();
        }

        return new WebResult(resultKey, Boolean.FALSE, infoKey, msg);
    }


    /**
     * <p>success.</p>
     *
     * @return a {@link } object.
     */
    public static WebResult failure(String msg) {
        return new WebResult(Boolean.FALSE, msg);
    }

    /**
     * <p>failure.</p>
     *
     * @param errors a {@link Collection} object.
     * @return a {@link } object.
     */
    public static WebResult failure(Collection<String> errors) {
        StringBuilder sb = new StringBuilder();

        for (String error : errors) {
            sb.append(error).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("。");
        }
        return new WebResult(Boolean.FALSE, sb.toString());
    }

    /**
     * <p>put.</p>
     *
     * @param key   a {@link String} object.
     * @param value a {@link Object} object.
     * @return a {@link } object.
     */
    public WebResult put(String key, Object value) {
        if (resultKey.equalsIgnoreCase(key) || infoKey.equalsIgnoreCase(key)) {
            throw new IllegalArgumentException(
                    String.format("put属性不能包含:%s 或 %s 名称一样的属性名", resultKey, infoKey)
            );
        }

        elements.put(key, value);
        return this;
    }

    /**
     * <p>putTotal.</p>
     *
     * @param total a {@link Long} object.
     * @return a {@link } object.
     */
    public WebResult putTotal(Long total) {
        elements.put(TOTAL_KEY, total);
        return this;
    }

    /**
     * <p>putRows.</p>
     *
     * @param rows a {@link List} object.
     * @return a {@link } object.
     */
    public WebResult putRows(List<?> rows) {
        elements.put(ROWS_KEY, rows);
        return this;
    }

    /**
     * <p>putRowsAndItSizeIsTotal.</p>
     *
     * @param rows a {@link List} object.
     * @return a {@link } object.
     */
    public WebResult putRowsAndItSizeIsTotal(List<?> rows) {
        putTotal(new Long(rows.size()));
        putRows(rows);
        return this;
    }

    /**
     * <p>mergeScrollResult.</p>
     *
     * @param scrollResult a {@link Map} object.
     * @return a {@link } object.
     */
    public WebResult mergeScrollResult(Map<String, Object> scrollResult) {
        elements.putAll(scrollResult);
        return this;
    }

    /**
     * <p>resultKey.</p>
     *
     * @param key a {@link String} object.
     * @return a {@link } object.
     */
    public WebResult resultKey(String key) {
        this.resultKey = key;
        return this;
    }

    /**
     * <p>infoKey.</p>
     *
     * @param key a {@link String} object.
     * @return a {@link } object.
     */
    public WebResult infoKey(String key) {
        this.infoKey = key;
        return this;
    }

    /**
     * <p>info.</p>
     *
     * @param info a {@link String} object.
     * @return a {@link } object.
     */
    public WebResult info(String info) {
        this.info = info;
        return this;
    }

    /**
     * <p>toMap.</p>
     *
     * @return a {@link Map} object.
     */
    public Map<String, Object> toMap() {
        Map<String, Object> results = new HashMap<String, Object>();

        results.put(this.resultKey, this.result);
        results.put(this.infoKey, this.info);

        for (Map.Entry<String, Object> entry : elements.entrySet()) {
            results.put(entry.getKey(), entry.getValue());
        }

        return results;
    }

    private void checkContainsKeys(Map<String, Object> mapping) {
        if (mapping.containsKey(resultKey) || mapping.containsKey(infoKey)) {
            throw new IllegalArgumentException(
                    String.format("mapping 的对象不能包含:%s 或 %s 名称一样的属性名", resultKey, infoKey)
            );
        }
    }

    /**
     * <p>Getter for the field <code>resultKey</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getResultKey() {
        return resultKey;
    }

    /**
     * <p>Getter for the field <code>infoKey</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getInfoKey() {
        return infoKey;
    }

    /**
     * <p>Getter for the field <code>result</code>.</p>
     *
     * @return a {@link Boolean} object.
     */
    public Boolean getResult() {
        return result;
    }

    /**
     * <p>Getter for the field <code>info</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getInfo() {
        return info;
    }

    /**
     * <p>putEmptyRowsAndItSizeIsTotal.</p>
     *
     * @return a {@link } object.
     */
    public WebResult putEmptyRowsAndItSizeIsTotal() {
        return putRowsAndItSizeIsTotal(new ArrayList<Object>());
    }
}
