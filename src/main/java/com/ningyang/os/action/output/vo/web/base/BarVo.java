package com.ningyang.os.action.output.vo.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/09/28 17:01
 * @描述：导航栏
 */
@Data
public class BarVo {

    private Long barId;

    private String barName;

    private String barPath;

    private String barIcon;

    private Integer barSort;

    private String barState;

    private String remark;
}
