package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 品牌产品系列图片
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@TableName("t_ser_brand_series_product_file")
public class SerBrandSeriesProductFile extends Model<SerBrandSeriesProductFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 系列id
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 系列图片id
     */
    @TableField("file_id")
    private Long fileId;


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

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SerBrandSeriesProductFile{" +
                "id=" + id +
                ", productId=" + productId +
                ", fileId=" + fileId +
                "}";
    }
}
