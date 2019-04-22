package com.ningyang.os.action.output.vo.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/10/03 13:32
 * @描述：系统用户
 */
@Data
public class SysUserVo {
    //
    private Long userId;
    //
    private String userName;
    //
    private String loginName;
    //
    private Integer userType;
    //
    private Integer userState;
    //
    private Long pid;
}
