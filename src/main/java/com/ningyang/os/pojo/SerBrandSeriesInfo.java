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
 * 品牌系列信息
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@TableName("t_ser_brand_series_info")
public class SerBrandSeriesInfo extends Model<SerBrandSeriesInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 品牌id
     */
    @TableField("brand_id")
    private Long brandId;
    /**
     * 品牌名称
     */
    @TableField("brand_name")
    private String brandName;
    /**
     * 系列名称
     */
    @TableField("series_name")
    private String seriesName;
    /**
     * 简略标题
     */
    @TableField("short_title")
    private String shortTitle;
    /**
     * 关键字
     */
    @TableField("key_word")
    private String keyWord;
    /**
     * 系列备注
     */
    @TableField("series_remark")
    private String seriesRemark;
    /**
     * 系列状态（0：使用，1：未使用）
     */
    @TableField("series_state")
    private Integer seriesState;
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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getSeriesRemark() {
        return seriesRemark;
    }

    public void setSeriesRemark(String seriesRemark) {
        this.seriesRemark = seriesRemark;
    }

    public Integer getSeriesState() {
        return seriesState;
    }

    public void setSeriesState(Integer seriesState) {
        this.seriesState = seriesState;
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
        return "SerBrandSeriesInfo{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", brandName=" + brandName +
                ", seriesName=" + seriesName +
                ", shortTitle=" + shortTitle +
                ", keyWord=" + keyWord +
                ", seriesRemark=" + seriesRemark +
                ", seriesState=" + seriesState +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
