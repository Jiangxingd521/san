package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/10/04 23:55
 * @描述：
 */
@Data
public class UserRoleCommand {

    private Long userId;

    private List<Long> ids;
}
