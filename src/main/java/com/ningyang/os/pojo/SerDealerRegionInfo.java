package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 经销商区域
 * </p>
 *
 * @author kaider
 * @since 2018-11-15
 */
@TableName("t_ser_dealer_region_info")
public class SerDealerRegionInfo extends Model<SerDealerRegionInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 经销商id
     */
    @TableField("dealer_id")
    private Long dealerId;
    /**
     * 区域id
     */
    @TableField("region_id")
    private Long regionId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SerDealerRegionInfo{" +
                "id=" + id +
                ", dealerId=" + dealerId +
                ", regionId=" + regionId +
                "}";
    }
}
