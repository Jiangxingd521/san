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
 * 商品出库记录日志
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@TableName("l_ser_warehouse_goods_out_info")
public class LSerWarehouseGoodsOutInfo extends Model<LSerWarehouseGoodsOutInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 销售订单
     */
    @TableField("order_id")
    private Long orderId;
    /**
     * 仓库id
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 箱码
     */
    @TableField("box_no")
    private String boxNo;
    /**
     * 产品id
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 经销商id
     */
    @TableField("dealer_id")
    private Long dealerId;
    /**
     * 操作人
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 出库时间
     */
    @TableField("goods_out_time")
    private Date goodsOutTime;
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

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getGoodsOutTime() {
        return goodsOutTime;
    }

    public void setGoodsOutTime(Date goodsOutTime) {
        this.goodsOutTime = goodsOutTime;
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
        return "LSerWarehouseGoodsOutInfo{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", warehouseId=" + warehouseId +
                ", boxNo=" + boxNo +
                ", productId=" + productId +
                ", dealerId=" + dealerId +
                ", userId=" + userId +
                ", goodsOutTime=" + goodsOutTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
