package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 品牌系列产品信息
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@TableName("t_ser_brand_series_product_info")
public class SerBrandSeriesProductInfo extends Model<SerBrandSeriesProductInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 系列id
     */
    @TableField("series_id")
    private Long seriesId;
    /**
     * 系列名称
     */
    @TableField("series_name")
    private String seriesName;
    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;
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
     * 码数量
     */
    @TableField("code_number")
    private Integer codeNumber;
    /**
     * 产品规格
     */
    @TableField("series_standard")
    private String seriesStandard;
    /**
     * 市场价格
     */
    @TableField("market_price")
    private BigDecimal marketPrice;
    /**
     * 销售价格
     */
    @TableField("sales_price")
    private BigDecimal salesPrice;
    /**
     * 产品69码
     */
    @TableField("code_69")
    private String code69;
    /**
     * 产品备注
     */
    @TableField("product_remark")
    private String productRemark;
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
    /**
     * 产品状态（0：使用，1：未使用）
     */
    @TableField("product_state")
    private Integer productState;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Integer getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(Integer codeNumber) {
        this.codeNumber = codeNumber;
    }

    public String getSeriesStandard() {
        return seriesStandard;
    }

    public void setSeriesStandard(String seriesStandard) {
        this.seriesStandard = seriesStandard;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getCode69() {
        return code69;
    }

    public void setCode69(String code69) {
        this.code69 = code69;
    }

    public String getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark;
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

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SerBrandSeriesProductInfo{" +
                "id=" + id +
                ", seriesId=" + seriesId +
                ", seriesName=" + seriesName +
                ", productName=" + productName +
                ", shortTitle=" + shortTitle +
                ", keyWord=" + keyWord +
                ", codeNumber=" + codeNumber +
                ", seriesStandard=" + seriesStandard +
                ", marketPrice=" + marketPrice +
                ", salesPrice=" + salesPrice +
                ", code69=" + code69 +
                ", productRemark=" + productRemark +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", productState=" + productState +
                "}";
    }
}
