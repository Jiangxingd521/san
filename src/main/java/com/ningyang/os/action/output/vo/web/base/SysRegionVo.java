package com.ningyang.os.action.output.vo.web.base;

import com.ningyang.os.action.utils.TreeEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/12 18:09
 * @描述：区域
 */
@Data
public class SysRegionVo implements TreeEntity<SysRegionVo> {

    private String id;

    private String parentId;
    //名称
    private String label;

    private String value;
    //子菜单
    private List<SysRegionVo> children;
}
