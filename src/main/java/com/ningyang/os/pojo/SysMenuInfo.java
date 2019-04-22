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
 * 菜单信息
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@TableName("t_sys_menu_info")
public class SysMenuInfo extends Model<SysMenuInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 类型（0：菜单，1：按钮）
     */
    @TableField("menu_type")
    private Integer menuType;
    /**
     * 图标链接
     */
    @TableField("menu_icon")
    private String menuIcon;
    /**
     * 菜单链接
     */
    @TableField("menu_path")
    private String menuPath;
    /**
     * 菜单许可标识
     */
    @TableField("menu_permission")
    private String menuPermission;
    /**
     * 排序
     */
    @TableField("menu_sort")
    private Integer menuSort;
    /**
     * 状态（0：有效，1：无效）
     */
    @TableField("menu_state")
    private Integer menuState;
    /**
     * 描述
     */
    @TableField("menu_remark")
    private String menuRemark;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuPermission() {
        return menuPermission;
    }

    public void setMenuPermission(String menuPermission) {
        this.menuPermission = menuPermission;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Integer getMenuState() {
        return menuState;
    }

    public void setMenuState(Integer menuState) {
        this.menuState = menuState;
    }

    public String getMenuRemark() {
        return menuRemark;
    }

    public void setMenuRemark(String menuRemark) {
        this.menuRemark = menuRemark;
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
        return "SysMenuInfo{" +
                "id=" + id +
                ", pid=" + pid +
                ", menuName=" + menuName +
                ", menuType=" + menuType +
                ", menuIcon=" + menuIcon +
                ", menuPath=" + menuPath +
                ", menuPermission=" + menuPermission +
                ", menuSort=" + menuSort +
                ", menuState=" + menuState +
                ", menuRemark=" + menuRemark +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
