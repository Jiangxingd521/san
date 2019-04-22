package com.ningyang.os.action.output.vo.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/13 15:08
 * @描述：地区区域
 */
@Data
public class RegionVo {

    private String id;

    private String parentId;
    //名称
    private String label;

}
