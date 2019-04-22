package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/10/04 21:14
 * @描述：系统用户
 */
@Data
public class UserCommand {

    private Long userId;

    private Long pid;

    private String userName;

    private String loginName;

    private Integer userType;

    private Integer userState;

    private boolean password;
}
