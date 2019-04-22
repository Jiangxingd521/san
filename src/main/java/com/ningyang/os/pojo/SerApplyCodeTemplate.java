package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

/**
 * @Author： kaider
 * @Date：2018/11/20 15:09
 * @描述：溯源码模板
 */
public class SerApplyCodeTemplate {

    private Long id;
    @TableField("center_id")
    private Long centerId;
    @TableField("code_order")
    private String codeOrder;
    @TableField("code_content")
    private String codeContent;
    //溯源码位置
    @TableField("code_position")
    private int codePosition;
    //溯源码位置类型
    @TableField("code_position_type")
    private int codePositionType;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public int getCodePosition() {
        return codePosition;
    }

    public void setCodePosition(int codePosition) {
        this.codePosition = codePosition;
    }

    public int getCodePositionType() {
        return codePositionType;
    }

    public void setCodePositionType(int codePositionType) {
        this.codePositionType = codePositionType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
