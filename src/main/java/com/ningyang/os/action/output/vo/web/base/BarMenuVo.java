package com.ningyang.os.action.output.vo.web.base;

import com.ningyang.os.action.utils.TreeEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/10/01 16:17
 * @描述：导航栏对应的菜单
 */
@Data
public class BarMenuVo implements TreeEntity<BarMenuVo> {
    //菜单id
    private String id;
    //父菜单id
    private String parentId;
    //菜单名
    private String label;
    //菜单链接
    private String href;
    //图标
    private String icon;
    //子菜单
    private List<BarMenuVo> children;
}
