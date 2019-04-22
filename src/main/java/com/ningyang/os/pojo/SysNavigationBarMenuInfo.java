package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 菜单关联导航
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@TableName("t_sys_navigation_bar_menu_info")
public class SysNavigationBarMenuInfo extends Model<SysNavigationBarMenuInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 导航栏id
     */
    @TableField("bar_id")
    private Long barId;
    /**
     * 菜单id
     */
    @TableField("menu_id")
    private Long menuId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBarId() {
        return barId;
    }

    public void setBarId(Long barId) {
        this.barId = barId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysNavigationBarMenuInfo{" +
                "id=" + id +
                ", barId=" + barId +
                ", menuId=" + menuId +
                "}";
    }
}
