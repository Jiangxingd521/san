package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/10/02 15:52
 * @描述：角色授权
 */
@Data
public class RoleBarMenuCommand {

    private Long roleId;

    private List<Long> ids;
}
