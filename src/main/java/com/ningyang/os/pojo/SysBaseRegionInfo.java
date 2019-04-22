package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 系统区域信息表
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@TableName("t_sys_base_region_info")
public class SysBaseRegionInfo extends Model<SysBaseRegionInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 地区名称
     */
    @TableField("region_name")
    private String regionName;


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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysBaseRegionInfo{" +
                "id=" + id +
                ", pid=" + pid +
                ", regionName=" + regionName +
                "}";
    }
}
