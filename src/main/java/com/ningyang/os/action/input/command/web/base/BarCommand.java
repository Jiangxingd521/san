package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/10/01 22:44
 * @描述：导航栏
 */
@Data
public class BarCommand {

    private Long barId;

    private String barName;

    private String barSort;

    private String barPath;

    private String barIcon;

    private Integer barState;

    private String remark;
}
