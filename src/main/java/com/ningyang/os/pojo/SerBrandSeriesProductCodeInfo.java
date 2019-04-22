package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 产品组成码信息
 * </p>
 *
 * @author kaider
 * @since 2018-11-14
 */
@TableName("t_ser_brand_series_product_code_info")
public class SerBrandSeriesProductCodeInfo extends Model<SerBrandSeriesProductCodeInfo> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 产品id
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 码类型id
     */
    @TableField("code_id")
    private Long codeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SerBrandSeriesProductCodeInfo{" +
                "id=" + id +
                ", productId=" + productId +
                ", codeId=" + codeId +
                "}";
    }
}
