package com.ningyang.os.action.input.condition.base;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/09/27 14:28
 * @描述：用户
 */
@Data
public class QueryUserCondition extends BaseCondition {

    private String username;

    private String password;

    private String pid;

    private int userFlag;

    private Integer userState;

    private Integer userType;
    //授权码
    private String authorizationCode;
}
