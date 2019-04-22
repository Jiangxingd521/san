package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 供应商信息
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@TableName("t_ser_dealer_info")
public class SerDealerInfo extends Model<SerDealerInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 供应商名称
     */
    @TableField("dealer_name")
    private String dealerName;
    /**
     * 联系人
     */
    @TableField("person_name")
    private String personName;
    /**
     * 供应商电话
     */
    @TableField("person_mobile")
    private String personMobile;
    /**
     * 与供应商合作状态（0：合作，1：不合作）
     */
    @TableField("dealer_state")
    private Integer dealerState;
    /**
     * 供应商区域id
     */
    @TableField("region_id")
    private Long regionId;
    /**
     * 供应商详细地址
     */
    @TableField("dealer_address")
    private String dealerAddress;
    /**
     * 供应商社会码
     */
    @TableField("social_code")
    private String socialCode;
    /**
     * 备注
     */
    @TableField("dealer_remark")
    private String dealerRemark;
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
    //经销商状态（0：启用，1：禁用）
    private int idata1;

    public int getIdata1() {
        return idata1;
    }

    public void setIdata1(int idata1) {
        this.idata1 = idata1;
    }
    private int idata2;

    public int getIdata2() {
        return idata2;
    }

    public void setIdata2(int idata2) {
        this.idata2 = idata2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public Integer getDealerState() {
        return dealerState;
    }

    public void setDealerState(Integer dealerState) {
        this.dealerState = dealerState;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public String getSocialCode() {
        return socialCode;
    }

    public void setSocialCode(String socialCode) {
        this.socialCode = socialCode;
    }

    public String getDealerRemark() {
        return dealerRemark;
    }

    public void setDealerRemark(String dealerRemark) {
        this.dealerRemark = dealerRemark;
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
        return this.id;
    }

    @Override
    public String toString() {
        return "SerDealerInfo{" +
                "id=" + id +
                ", dealerName='" + dealerName + '\'' +
                ", personName='" + personName + '\'' +
                ", personMobile='" + personMobile + '\'' +
                ", dealerState=" + dealerState +
                ", regionId=" + regionId +
                ", dealerAddress='" + dealerAddress + '\'' +
                ", socialCode='" + socialCode + '\'' +
                ", dealerRemark='" + dealerRemark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", idata1=" + idata1 +
                '}';
    }
}
