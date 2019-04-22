package com.ningyang.os.action.input.command.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/19 17:49
 * @描述：接口用
 */
@Data
public class ApiCommand {
    //用户授权码
    private String userCode;
    //接口授权码
    private String apiCode;
}
