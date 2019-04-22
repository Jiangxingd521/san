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
 * 商品入库
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@TableName("t_ser_warehouse_goods_info")
public class SerWarehouseGoodsInfo extends Model<SerWarehouseGoodsInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 入库来源（0：生产入库，1：换货入库，2：退货入库，3：换仓入库）
     */
    @TableField("source_type")
    private Integer sourceType;
    /**
     * 仓库id
     */
    @TableField("warehouse_id")
    private Long warehouseId;
    /**
     * 商品id
     */
    @TableField("goods_id")
    private Long goodsId;
    /**
     * 箱码
     */
    @TableField("box_no")
    private String boxNo;
    /**
     * 商品状态（0：未入库，1：入库，2：出库，3：丢失，4：已扫描兑奖）
     */
    @TableField("goods_state")
    private Integer goodsState;
    /**
     * 入库编号
     */
    @TableField("warehouse_in_no")
    private String warehouseInNo;
    /**
     * 入库人
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 入库备注
     */
    private String remark;
    /**
     * 入库时间
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

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public Integer getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(Integer goodsState) {
        this.goodsState = goodsState;
    }

    public String getWarehouseInNo() {
        return warehouseInNo;
    }

    public void setWarehouseInNo(String warehouseInNo) {
        this.warehouseInNo = warehouseInNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "SerWarehouseGoodsInfo{" +
                "id=" + id +
                ", warehouseId=" + warehouseId +
                ", goodsId=" + goodsId +
                ", boxNo=" + boxNo +
                ", goodsState=" + goodsState +
                ", warehouseInNo=" + warehouseInNo +
                ", userId=" + userId +
                ", remark=" + remark +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
