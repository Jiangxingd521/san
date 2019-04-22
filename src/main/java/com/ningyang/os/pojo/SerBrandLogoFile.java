package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 品牌logo信息
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@TableName("t_ser_brand_logo_file")
public class SerBrandLogoFile extends Model<SerBrandLogoFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 品牌
     */
    @TableField("brand_id")
    private Long brandId;
    /**
     * 品牌logo文件id
     */
    @TableField("file_id")
    private Long fileId;


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
        return "SerBrandLogoFile{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", fileId=" + fileId +
                "}";
    }
}
