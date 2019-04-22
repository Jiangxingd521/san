package com.ningyang.os.action.output.dto.web;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/10/18 11:37
 * @描述：上传文件的信息
 */
@Data
public class FileUploadDto {

    private Long id;

    private String name;

    private String url;

    private String status;

    private Long uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
