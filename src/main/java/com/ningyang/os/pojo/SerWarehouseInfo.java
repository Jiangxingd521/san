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
 * 仓库信息
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@TableName("t_ser_warehouse_info")
public class SerWarehouseInfo extends Model<SerWarehouseInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 仓库名称
     */
    @TableField("warehouse_name")
    private String warehouseName;
    /**
     * 系统用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 仓库管理员
     */
    @TableField("warehouse_person")
    private String warehousePerson;
    /**
     * 仓库管理员电话
     */
    @TableField("warehouse_person_mobile")
    private String warehousePersonMobile;
    /**
     * 库存总量
     */
    @TableField("total_inventory")
    private String totalInventory;
    /**
     * 仓库备注
     */
    @TableField("warehouse_remark")
    private String warehouseRemark;
    /**
     * 仓库状态（0：未使用，1：使用）
     */
    @TableField("warehouse_state")
    private Integer warehouseState;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 维度
     */
    private Double latitude;
    /**
     * 清点库存（0：不清点，1：清点）
     */
    @TableField("is_inventory")
    private Integer isInventory;
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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWarehousePerson() {
        return warehousePerson;
    }

    public void setWarehousePerson(String warehousePerson) {
        this.warehousePerson = warehousePerson;
    }

    public String getWarehousePersonMobile() {
        return warehousePersonMobile;
    }

    public void setWarehousePersonMobile(String warehousePersonMobile) {
        this.warehousePersonMobile = warehousePersonMobile;
    }

    public String getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(String totalInventory) {
        this.totalInventory = totalInventory;
    }

    public String getWarehouseRemark() {
        return warehouseRemark;
    }

    public void setWarehouseRemark(String warehouseRemark) {
        this.warehouseRemark = warehouseRemark;
    }

    public Integer getWarehouseState() {
        return warehouseState;
    }

    public void setWarehouseState(Integer warehouseState) {
        this.warehouseState = warehouseState;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getIsInventory() {
        return isInventory;
    }

    public void setIsInventory(Integer isInventory) {
        this.isInventory = isInventory;
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
        return "SerWarehouseInfo{" +
                "id=" + id +
                ", warehouseName=" + warehouseName +
                ", userId=" + userId +
                ", warehousePerson=" + warehousePerson +
                ", warehousePersonMobile=" + warehousePersonMobile +
                ", totalInventory=" + totalInventory +
                ", warehouseRemark=" + warehouseRemark +
                ", warehouseState=" + warehouseState +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", isInventory=" + isInventory +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
