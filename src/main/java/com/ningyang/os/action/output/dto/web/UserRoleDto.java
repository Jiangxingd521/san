package com.ningyang.os.action.output.dto.web;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/09/27 14:58
 * @描述：用户角色信息
 */
@Data
public class UserRoleDto {

    private Long id;

    private Long userId;

    private Long roleId;

    private int roleFlag;
}
