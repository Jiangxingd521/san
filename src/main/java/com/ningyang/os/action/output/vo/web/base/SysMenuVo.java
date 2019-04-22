package com.ningyang.os.action.output.vo.web.base;

import com.ningyang.os.action.utils.TreeEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/10/02 00:04
 * @描述：菜单
 */
@Data
public class SysMenuVo implements TreeEntity<SysMenuVo> {
    //菜单id
    private String id;
    //菜单父id
    private String parentId;
    //名称
    private String label;
    //标识
    private String code;
    //类型
    private Integer type;
    //排序
    private int sort;
    //路径
    private String path;
    //图标
    private String icon;
    //备注
    private String remark;
    //子菜单
    private List<SysMenuVo> children;
}
