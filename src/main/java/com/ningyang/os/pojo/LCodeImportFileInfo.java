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
 * 溯源码文件上传记录
 * </p>
 *
 * @author kaider
 * @since 2018-11-22
 */
@TableName("l_code_import_file_info")
public class LCodeImportFileInfo extends Model<LCodeImportFileInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 上传的文件名
     */
    @TableField("file_name")
    private String fileName;
    /**
     * 文件存放路径
     */
    @TableField("file_path")
    private String filePath;
    /**
     * 上传的订单
     */
    @TableField("upload_order")
    private String uploadOrder;
    /**
     * 操作人
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 溯源码个数
     */
    @TableField("code_count")
    private Long codeCount;
    /**
     * 模板id
     */
    @TableField("template_id")
    private Long templateId;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUploadOrder() {
        return uploadOrder;
    }

    public void setUploadOrder(String uploadOrder) {
        this.uploadOrder = uploadOrder;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(Long codeCount) {
        this.codeCount = codeCount;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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
        return "LCodeImportFileInfo{" +
                "id=" + id +
                ", fileName=" + fileName +
                ", filePath=" + filePath +
                ", uploadOrder=" + uploadOrder +
                ", userId=" + userId +
                ", codeCount=" + codeCount +
                ", templateId=" + templateId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
