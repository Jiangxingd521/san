package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 角色关联导航栏
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@TableName("t_sys_role_navigation_bar_info")
public class SysRoleNavigationBarInfo extends Model<SysRoleNavigationBarInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;
    /**
     * 导航栏id
     */
    @TableField("bar_id")
    private Long barId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getBarId() {
        return barId;
    }

    public void setBarId(Long barId) {
        this.barId = barId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysRoleNavigationBarInfo{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", barId=" + barId +
                "}";
    }
}
