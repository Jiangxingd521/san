package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/10/02 13:40
 * @描述：角色
 */
@Data
public class RoleCommand {

    private Long roleId;

    private String roleName;

    private Integer roleState;
}
