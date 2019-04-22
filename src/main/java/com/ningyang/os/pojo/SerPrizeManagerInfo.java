package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 奖项管理
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@TableName("t_ser_prize_manager_info")
public class SerPrizeManagerInfo extends Model<SerPrizeManagerInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("prize_manage_id")
    private Long prizeManageId;
    /**
     * 奖项类型
     */
    @TableField("prize_type_id")
    private Long prizeTypeId;
    /**
     * 奖项名称
     */
    @TableField("prize_name")
    private String prizeName;
    /**
     * 奖项内容
     */
    @TableField("prize_content")
    private String prizeContent;
    /**
     * 奖项备注
     */
    @TableField("prize_remark")
    private String prizeRemark;
    /**
     * 操作人
     */
    @TableField("user_id")
    private Long userId;
    private Integer idata1;
    private Integer idata2;
    private Integer idata3;
    private Integer idata4;
    private String sdata1;
    private String sdata2;
    private String sdata3;
    private String sdata4;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Long getPrizeManageId() {
        return prizeManageId;
    }

    public void setPrizeManageId(Long prizeManageId) {
        this.prizeManageId = prizeManageId;
    }

    public Long getPrizeTypeId() {
        return prizeTypeId;
    }

    public void setPrizeTypeId(Long prizeTypeId) {
        this.prizeTypeId = prizeTypeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeContent() {
        return prizeContent;
    }

    public void setPrizeContent(String prizeContent) {
        this.prizeContent = prizeContent;
    }

    public String getPrizeRemark() {
        return prizeRemark;
    }

    public void setPrizeRemark(String prizeRemark) {
        this.prizeRemark = prizeRemark;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIdata1() {
        return idata1;
    }

    public void setIdata1(Integer idata1) {
        this.idata1 = idata1;
    }

    public Integer getIdata2() {
        return idata2;
    }

    public void setIdata2(Integer idata2) {
        this.idata2 = idata2;
    }

    public Integer getIdata3() {
        return idata3;
    }

    public void setIdata3(Integer idata3) {
        this.idata3 = idata3;
    }

    public Integer getIdata4() {
        return idata4;
    }

    public void setIdata4(Integer idata4) {
        this.idata4 = idata4;
    }

    public String getSdata1() {
        return sdata1;
    }

    public void setSdata1(String sdata1) {
        this.sdata1 = sdata1;
    }

    public String getSdata2() {
        return sdata2;
    }

    public void setSdata2(String sdata2) {
        this.sdata2 = sdata2;
    }

    public String getSdata3() {
        return sdata3;
    }

    public void setSdata3(String sdata3) {
        this.sdata3 = sdata3;
    }

    public String getSdata4() {
        return sdata4;
    }

    public void setSdata4(String sdata4) {
        this.sdata4 = sdata4;
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

    @Override
    protected Serializable pkVal() {
        return this.prizeManageId;
    }

    @Override
    public String toString() {
        return "SerPrizeManagerInfo{" +
                "prizeManageId=" + prizeManageId +
                ", prizeTypeId=" + prizeTypeId +
                ", prizeName=" + prizeName +
                ", prizeContent=" + prizeContent +
                ", prizeRemark=" + prizeRemark +
                ", userId=" + userId +
                ", idata1=" + idata1 +
                ", idata2=" + idata2 +
                ", idata3=" + idata3 +
                ", idata4=" + idata4 +
                ", sdata1=" + sdata1 +
                ", sdata2=" + sdata2 +
                ", sdata3=" + sdata3 +
                ", sdata4=" + sdata4 +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
