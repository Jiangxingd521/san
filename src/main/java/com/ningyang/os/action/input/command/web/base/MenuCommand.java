package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/10/02 11:16
 * @描述：菜单
 */
@Data
public class MenuCommand {

    private Long id;

    private Long parentId;

    private String label;

    private String code;

    private Integer type;

    private Integer sort;

    private String icon;

    private String path;

    private Integer menuState;

    private String remark;
}
