package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/10/21 16:13
 * @描述：用户密码
 */
@Data
public class UserPwdCommand {

    private String userId;

    private String userPassword;
}
