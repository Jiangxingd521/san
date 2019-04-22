package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 奖项设定
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@TableName("t_ser_prize_set_info")
public class SerPrizeSetInfo extends Model<SerPrizeSetInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("prize_set_id")
    private Long prizeSetId;
    /**
     * 奖项管理id
     */
    @TableField("prize_manager_id")
    private Long prizeManagerId;

    /**
     * 布奖名称
     */
    @TableField("prize_set_name")
    private String prizeSetName;
    /**
     * 产品id
     */
    @TableField("prod_id")
    private Long prodId;
    /**
     * 会员类型
     */
    @TableField("member_type")
    private Long memberType;
    /**
     * 布奖区域
     */
    @TableField("region_id")
    private Long regionId;
    /**
     * 布奖数量
     */
    @TableField("prize_quantity")
    private Integer prizeQuantity;
    /**
     * 红包额度
     */
    private BigDecimal money;
    /**
     * 红包额度结束
     */
    @TableField("money_end")
    private BigDecimal moneyEnd;
    /**
     * 积分额度
     */
    private Integer ponit;
    /**
     * 积分额度结束
     */
    @TableField("point_end")
    private Integer pointEnd;
    /**
     * 布奖类型(1:限制，2：不限制)
     */
    @TableField("prize_set_type")
    private Integer prizeSetType;
    /**
     * 布奖模式(1：随机，2：平均)
     */
    @TableField("prize_mode_type")
    private Integer prizeModeType;
    /**
     * 卡券消费额度
     */
    @TableField("card_money")
    private Integer cardMoney;
    /**
     * 卡券优惠额度
     */
    @TableField("card_coupon_money")
    private Integer cardCouponMoney;
    /**
     * 布奖开始日期
     */
    @TableField("prize_start_date")
    private Date prizeStartDate;
    /**
     * 布奖结束日期
     */
    @TableField("prize_end_date")
    private Date prizeEndDate;
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


    public Long getPrizeSetId() {
        return prizeSetId;
    }

    public void setPrizeSetId(Long prizeSetId) {
        this.prizeSetId = prizeSetId;
    }

    public Long getPrizeManagerId() {
        return prizeManagerId;
    }

    public void setPrizeManagerId(Long prizeManagerId) {
        this.prizeManagerId = prizeManagerId;
    }

    public String getPrizeSetName() {
        return prizeSetName;
    }

    public void setPrizeSetName(String prizeSetName) {
        this.prizeSetName = prizeSetName;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getMemberType() {
        return memberType;
    }

    public void setMemberType(Long memberType) {
        this.memberType = memberType;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Integer getPrizeQuantity() {
        return prizeQuantity;
    }

    public void setPrizeQuantity(Integer prizeQuantity) {
        this.prizeQuantity = prizeQuantity;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoneyEnd() {
        return moneyEnd;
    }

    public void setMoneyEnd(BigDecimal moneyEnd) {
        this.moneyEnd = moneyEnd;
    }

    public Integer getPonit() {
        return ponit;
    }

    public void setPonit(Integer ponit) {
        this.ponit = ponit;
    }

    public Integer getPointEnd() {
        return pointEnd;
    }

    public void setPointEnd(Integer pointEnd) {
        this.pointEnd = pointEnd;
    }

    public Integer getPrizeSetType() {
        return prizeSetType;
    }

    public void setPrizeSetType(Integer prizeSetType) {
        this.prizeSetType = prizeSetType;
    }

    public Integer getPrizeModeType() {
        return prizeModeType;
    }

    public void setPrizeModeType(Integer prizeModeType) {
        this.prizeModeType = prizeModeType;
    }

    public Integer getCardMoney() {
        return cardMoney;
    }

    public void setCardMoney(Integer cardMoney) {
        this.cardMoney = cardMoney;
    }

    public Integer getCardCouponMoney() {
        return cardCouponMoney;
    }

    public void setCardCouponMoney(Integer cardCouponMoney) {
        this.cardCouponMoney = cardCouponMoney;
    }

    public Date getPrizeStartDate() {
        return prizeStartDate;
    }

    public void setPrizeStartDate(Date prizeStartDate) {
        this.prizeStartDate = prizeStartDate;
    }

    public Date getPrizeEndDate() {
        return prizeEndDate;
    }

    public void setPrizeEndDate(Date prizeEndDate) {
        this.prizeEndDate = prizeEndDate;
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
        return this.prizeSetId;
    }

    @Override
    public String toString() {
        return "SerPrizeSetInfo{" +
                "prizeSetId=" + prizeSetId +
                ", prizeSetName=" + prizeSetName +
                ", prodId=" + prodId +
                ", memberType=" + memberType +
                ", regionId=" + regionId +
                ", prizeQuantity=" + prizeQuantity +
                ", money=" + money +
                ", moneyEnd=" + moneyEnd +
                ", ponit=" + ponit +
                ", pointEnd=" + pointEnd +
                ", prizeSetType=" + prizeSetType +
                ", prizeModeType=" + prizeModeType +
                ", cardMoney=" + cardMoney +
                ", cardCouponMoney=" + cardCouponMoney +
                ", prizeStartDate=" + prizeStartDate +
                ", prizeEndDate=" + prizeEndDate +
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
