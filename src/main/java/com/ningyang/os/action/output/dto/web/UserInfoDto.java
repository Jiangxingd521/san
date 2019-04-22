package com.ningyang.os.action.output.dto.web;

import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/09/27 14:37
 * @描述：用户信息
 */
@Data
public class UserInfoDto {

    private String username;

    private String name;

    private List<String> roles;

    private List<String> permission;
}
