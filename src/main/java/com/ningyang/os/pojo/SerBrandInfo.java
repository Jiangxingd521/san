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
 * 企业品牌信息
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@TableName("t_ser_brand_info")
public class SerBrandInfo extends Model<SerBrandInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 品牌名称
     */
    @TableField("brand_name")
    private String brandName;
    /**
     * 简略标题
     */
    @TableField("short_title")
    private String shortTitle;
    /**
     * 排序
     */
    @TableField("brand_sort")
    private Integer brandSort;
    /**
     * 产地
     */
    @TableField("region_id")
    private Long regionId;
    /**
     * 品牌关键字
     */
    @TableField("brand_keyword")
    private String brandKeyword;
    /**
     * 品牌备注
     */
    @TableField("brand_remark")
    private String brandRemark;
    /**
     * 品牌状态（0：使用，1：未使用）
     */
    @TableField("brand_state")
    private Integer brandState;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public Integer getBrandSort() {
        return brandSort;
    }

    public void setBrandSort(Integer brandSort) {
        this.brandSort = brandSort;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getBrandKeyword() {
        return brandKeyword;
    }

    public void setBrandKeyword(String brandKeyword) {
        this.brandKeyword = brandKeyword;
    }

    public String getBrandRemark() {
        return brandRemark;
    }

    public void setBrandRemark(String brandRemark) {
        this.brandRemark = brandRemark;
    }

    public Integer getBrandState() {
        return brandState;
    }

    public void setBrandState(Integer brandState) {
        this.brandState = brandState;
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
        return "SerBrandInfo{" +
                "id=" + id +
                ", brandName=" + brandName +
                ", shortTitle=" + shortTitle +
                ", brandSort=" + brandSort +
                ", regionId=" + regionId +
                ", brandKeyword=" + brandKeyword +
                ", brandRemark=" + brandRemark +
                ", brandState=" + brandState +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
