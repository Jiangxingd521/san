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
 * 商品信息表
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@TableName("t_ser_goods_info")
public class SerGoodsInfo extends Model<SerGoodsInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 品牌
     */
    @TableField("brand_id")
    private Long brandId;
    /**
     * 品牌名称
     */
    @TableField("brand_name")
    private String brandName;
    /**
     * 系列
     */
    @TableField("brand_series_id")
    private Long brandSeriesId;
    /**
     * 系列名称
     */
    @TableField("brand_series_name")
    private String brandSeriesName;
    /**
     * 产品
     */
    @TableField("brand_series_product_id")
    private Long brandSeriesProductId;
    /**
     * 产品名称
     */
    @TableField("brand_series_product_name")
    private String brandSeriesProductName;
    /**
     * 内码
     */
    @TableField("M1")
    private String m1;
    /**
     * 外码
     */
    @TableField("M2")
    private String m2;
    /**
     * 外码
     */
    @TableField("M3")
    private String m3;
    /**
     * 外码
     */
    @TableField("M4")
    private String m4;
    /**
     * 外码
     */
    @TableField("M5")
    private String m5;
    /**
     * 外码
     */
    @TableField("M6")
    private String m6;
    /**
     * 外码
     */
    @TableField("M7")
    private String m7;
    /**
     * 外码
     */
    @TableField("M8")
    private String m8;
    /**
     * 外码
     */
    @TableField("M9")
    private String m9;
    /**
     * 外码
     */
    @TableField("M10")
    private String m10;
    /**
     * 码说明
     */
    @TableField("M1_remark")
    private String m1Remark;
    /**
     * 码说明
     */
    @TableField("M2_remark")
    private String m2Remark;
    /**
     * 码说明
     */
    @TableField("M3_remark")
    private String m3Remark;
    /**
     * 码说明
     */
    @TableField("M4_remark")
    private String m4Remark;
    /**
     * 码说明
     */
    @TableField("M5_remark")
    private String m5Remark;
    /**
     * 码说明
     */
    @TableField("M6_remark")
    private String m6Remark;
    /**
     * 码说明
     */
    @TableField("M7_remark")
    private String m7Remark;
    /**
     * 码说明
     */
    @TableField("M8_remark")
    private String m8Remark;
    /**
     * 码说明
     */
    @TableField("M9_remark")
    private String m9Remark;
    /**
     * 码说明
     */
    @TableField("M10_remark")
    private String m10Remark;
    /**
     * 商品状态
     */
    @TableField("goods_state")
    private Integer goodsState;
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

    public Long getBrandSeriesId() {
        return brandSeriesId;
    }

    public void setBrandSeriesId(Long brandSeriesId) {
        this.brandSeriesId = brandSeriesId;
    }

    public String getBrandSeriesName() {
        return brandSeriesName;
    }

    public void setBrandSeriesName(String brandSeriesName) {
        this.brandSeriesName = brandSeriesName;
    }

    public Long getBrandSeriesProductId() {
        return brandSeriesProductId;
    }

    public void setBrandSeriesProductId(Long brandSeriesProductId) {
        this.brandSeriesProductId = brandSeriesProductId;
    }

    public String getBrandSeriesProductName() {
        return brandSeriesProductName;
    }

    public void setBrandSeriesProductName(String brandSeriesProductName) {
        this.brandSeriesProductName = brandSeriesProductName;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }

    public String getM3() {
        return m3;
    }

    public void setM3(String m3) {
        this.m3 = m3;
    }

    public String getM4() {
        return m4;
    }

    public void setM4(String m4) {
        this.m4 = m4;
    }

    public String getM5() {
        return m5;
    }

    public void setM5(String m5) {
        this.m5 = m5;
    }

    public String getM6() {
        return m6;
    }

    public void setM6(String m6) {
        this.m6 = m6;
    }

    public String getM7() {
        return m7;
    }

    public void setM7(String m7) {
        this.m7 = m7;
    }

    public String getM8() {
        return m8;
    }

    public void setM8(String m8) {
        this.m8 = m8;
    }

    public String getM9() {
        return m9;
    }

    public void setM9(String m9) {
        this.m9 = m9;
    }

    public String getM10() {
        return m10;
    }

    public void setM10(String m10) {
        this.m10 = m10;
    }

    public String getM1Remark() {
        return m1Remark;
    }

    public void setM1Remark(String m1Remark) {
        this.m1Remark = m1Remark;
    }

    public String getM2Remark() {
        return m2Remark;
    }

    public void setM2Remark(String m2Remark) {
        this.m2Remark = m2Remark;
    }

    public String getM3Remark() {
        return m3Remark;
    }

    public void setM3Remark(String m3Remark) {
        this.m3Remark = m3Remark;
    }

    public String getM4Remark() {
        return m4Remark;
    }

    public void setM4Remark(String m4Remark) {
        this.m4Remark = m4Remark;
    }

    public String getM5Remark() {
        return m5Remark;
    }

    public void setM5Remark(String m5Remark) {
        this.m5Remark = m5Remark;
    }

    public String getM6Remark() {
        return m6Remark;
    }

    public void setM6Remark(String m6Remark) {
        this.m6Remark = m6Remark;
    }

    public String getM7Remark() {
        return m7Remark;
    }

    public void setM7Remark(String m7Remark) {
        this.m7Remark = m7Remark;
    }

    public String getM8Remark() {
        return m8Remark;
    }

    public void setM8Remark(String m8Remark) {
        this.m8Remark = m8Remark;
    }

    public String getM9Remark() {
        return m9Remark;
    }

    public void setM9Remark(String m9Remark) {
        this.m9Remark = m9Remark;
    }

    public String getM10Remark() {
        return m10Remark;
    }

    public void setM10Remark(String m10Remark) {
        this.m10Remark = m10Remark;
    }

    public Integer getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(Integer goodsState) {
        this.goodsState = goodsState;
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
        return "SerGoodsInfo{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", brandName=" + brandName +
                ", brandSeriesId=" + brandSeriesId +
                ", brandSeriesName=" + brandSeriesName +
                ", brandSeriesProductId=" + brandSeriesProductId +
                ", brandSeriesProductName=" + brandSeriesProductName +
                ", m1=" + m1 +
                ", m2=" + m2 +
                ", m3=" + m3 +
                ", m4=" + m4 +
                ", m5=" + m5 +
                ", m6=" + m6 +
                ", m7=" + m7 +
                ", m8=" + m8 +
                ", m9=" + m9 +
                ", m10=" + m10 +
                ", m1Remark=" + m1Remark +
                ", m2Remark=" + m2Remark +
                ", m3Remark=" + m3Remark +
                ", m4Remark=" + m4Remark +
                ", m5Remark=" + m5Remark +
                ", m6Remark=" + m6Remark +
                ", m7Remark=" + m7Remark +
                ", m8Remark=" + m8Remark +
                ", m9Remark=" + m9Remark +
                ", m10Remark=" + m10Remark +
                ", goodsState=" + goodsState +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
