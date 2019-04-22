package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author kaider
 * @since 2018-12-25
 */
@TableName("t_ser_prize_set_record_info")
public class SerPrizeSetRecordInfo extends Model<SerPrizeSetRecordInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 系列id
     */
    private Long seriesId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 仓库id
     */
    private Long warehouseId;

    /**
     * 奖项id
     */
    private Long prizeSetId;

    /**
     * 布奖种类（0：订单，1：产品）
     */
    private Integer prizeSetType;

    /**
     * 当前奖项设置的状态（0：有效，1：无效）
     */
    private Integer prizeSetState;

    /**
     * 奖项设置时的uuid
     */
    private String prizeSetUuid;

    /**
     * 奖项设置时受影响的数量
     */
    private Integer prizeSetCount;

    /**
     * 操作人
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getPrizeSetId() {
        return prizeSetId;
    }

    public void setPrizeSetId(Long prizeSetId) {
        this.prizeSetId = prizeSetId;
    }

    public Integer getPrizeSetType() {
        return prizeSetType;
    }

    public void setPrizeSetType(Integer prizeSetType) {
        this.prizeSetType = prizeSetType;
    }

    public Integer getPrizeSetState() {
        return prizeSetState;
    }

    public void setPrizeSetState(Integer prizeSetState) {
        this.prizeSetState = prizeSetState;
    }

    public String getPrizeSetUuid() {
        return prizeSetUuid;
    }

    public void setPrizeSetUuid(String prizeSetUuid) {
        this.prizeSetUuid = prizeSetUuid;
    }

    public Integer getPrizeSetCount() {
        return prizeSetCount;
    }

    public void setPrizeSetCount(Integer prizeSetCount) {
        this.prizeSetCount = prizeSetCount;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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
        return "SerPrizeSetRecordInfo{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", orderNo=" + orderNo +
        ", brandId=" + brandId +
        ", seriesId=" + seriesId +
        ", productId=" + productId +
        ", warehouseId=" + warehouseId +
        ", prizeSetId=" + prizeSetId +
        ", prizeSetType=" + prizeSetType +
        ", prizeSetState=" + prizeSetState +
        ", prizeSetUuid=" + prizeSetUuid +
        ", prizeSetCount=" + prizeSetCount +
        ", createUserId=" + createUserId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
