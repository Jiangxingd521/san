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
 * 销售订单表
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@TableName("t_ser_order_info")
public class SerOrderInfo extends Model<SerOrderInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 经销商
     */
    @TableField("dealer_id")
    private Long dealerId;
    /**
     * 经销商社会编码
     */
    @TableField("dealer_code")
    private String dealerCode;
    /**
     * 品牌
     */
    @TableField("brand_id")
    private Long brandId;
    /**
     * 产品
     */
    @TableField("series_id")
    private Long seriesId;
    /**
     * 系列
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 数量
     */
    @TableField("product_number")
    private String productNumber;
    /**
     * 订单状态（0：备单，1：确认订单，2：待发货，3：已发货未完成，4：已发货完成）
     */
    @TableField("order_state")
    private Integer orderState;
    /**
     * 备注
     */
    @TableField("order_remark")
    private String orderRemark;
    /**
     * 创建人
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 财务
     */
    @TableField("financial_id")
    private Long financialId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    //用户区别是pc操作还是app操作（1：pc，2：app）
    private Integer idata1;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
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

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFinancialId() {
        return financialId;
    }

    public void setFinancialId(Long financialId) {
        this.financialId = financialId;
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

    public Integer getIdata1() {
        return idata1;
    }

    public void setIdata1(Integer idata1) {
        this.idata1 = idata1;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
