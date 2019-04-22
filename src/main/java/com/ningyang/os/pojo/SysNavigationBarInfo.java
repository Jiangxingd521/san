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
 * 导航栏信息表
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@TableName("t_sys_navigation_bar_info")
public class SysNavigationBarInfo extends Model<SysNavigationBarInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 导航栏名称
     */
    @TableField("bar_name")
    private String barName;
    /**
     * 导航栏链接
     */
    @TableField("bar_path")
    private String barPath;
    /**
     * 导航栏图标
     */
    @TableField("bar_icon")
    private String barIcon;
    /**
     * 导航栏排序
     */
    @TableField("bar_sort")
    private String barSort;
    /**
     * 状态（0：有效，1：无效）
     */
    @TableField("bar_state")
    private Integer barState;
    /**
     * 备注
     */
    private String remark;
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

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public String getBarPath() {
        return barPath;
    }

    public void setBarPath(String barPath) {
        this.barPath = barPath;
    }

    public String getBarIcon() {
        return barIcon;
    }

    public void setBarIcon(String barIcon) {
        this.barIcon = barIcon;
    }

    public String getBarSort() {
        return barSort;
    }

    public void setBarSort(String barSort) {
        this.barSort = barSort;
    }

    public Integer getBarState() {
        return barState;
    }

    public void setBarState(Integer barState) {
        this.barState = barState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "SysNavigationBarInfo{" +
                "id=" + id +
                ", barName=" + barName +
                ", barPath=" + barPath +
                ", barIcon=" + barIcon +
                ", barISort=" + barSort +
                ", barState=" + barState +
                ", remark=" + remark +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
