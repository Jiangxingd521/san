package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 溯源码导入临时表
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
@TableName("t_ser_code_import_temp_info")
public class SerCodeImportTempInfo extends Model<SerCodeImportTempInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 模板id
     */
    @TableField("template_id")
    private Long templateId;
    /**
     * 左码
     */
    @TableField("left_code")
    private String leftCode;
    /**
     * 右码
     */
    @TableField("right_code")
    private String rightCode;
    /**
     * 左码码类型（盖内外盖之类）
     */
    @TableField("left_code_type")
    private Long leftCodeType;
    /**
     * 右码码类型（盖内外盖之类）
     */
    @TableField("right_code_type")
    private Long rightCodeType;
    @TableField("import_no")
    private String importNo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getLeftCode() {
        return leftCode;
    }

    public void setLeftCode(String leftCode) {
        this.leftCode = leftCode;
    }

    public String getRightCode() {
        return rightCode;
    }

    public void setRightCode(String rightCode) {
        this.rightCode = rightCode;
    }

    public Long getLeftCodeType() {
        return leftCodeType;
    }

    public void setLeftCodeType(Long leftCodeType) {
        this.leftCodeType = leftCodeType;
    }

    public Long getRightCodeType() {
        return rightCodeType;
    }

    public void setRightCodeType(Long rightCodeType) {
        this.rightCodeType = rightCodeType;
    }

    public String getImportNo() {
        return importNo;
    }

    public void setImportNo(String importNo) {
        this.importNo = importNo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SerCodeImportTempInfo{" +
                "id=" + id +
                ", templateId=" + templateId +
                ", leftCode=" + leftCode +
                ", rightCode=" + rightCode +
                ", leftCodeType=" + leftCodeType +
                ", rightCodeType=" + rightCodeType +
                "}";
    }
}
