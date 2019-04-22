package com.ningyang.os.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ningyang.os.action.input.condition.base.QueryUserCondition;
import com.ningyang.os.pojo.SysApiInfo;
import com.ningyang.os.pojo.SysUserInfo;
import com.ningyang.os.service.ISysApiInfoService;
import com.ningyang.os.service.ISysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ningyang.os.action.utils.JwtUtil.parseJWTToString;

/**
 * @Author： kaider
 * @Date：2018/07/19 11:11
 * @描述：公共controller
 */
public class BaseController {

    @Autowired
    private ISysUserInfoService userInfoService;
    @Autowired
    private ISysApiInfoService apiInfoService;


    /**
     * 通过userToken获取用户信息
     *
     * @param userToken
     * @return
     */
    public SysUserInfo getBaseUserInfo(String userToken) {
        String loginUserId = parseJWTToString(userToken);
        return userInfoService.getById(loginUserId);
    }

    /**
     * 通过授权码获取用户信息
     *
     * @param authorizationCode 企业授权码
     * @return
     */
    public SysUserInfo getSysUserInfoByCode(String authorizationCode) {
        QueryUserCondition condition = new QueryUserCondition();
        condition.setUserState(0);
        condition.setAuthorizationCode(authorizationCode);
        return userInfoService.findByCondition(condition);
    }

    /**
     * 获取授权码
     *
     * @param apiType 0：用户授权码，1：接口授权码
     * @return
     */
    public String getAuthorizationCode(int apiType) {
        SysApiInfo apiInfo = apiInfoService.getOne(new QueryWrapper<SysApiInfo>().eq("api_type", apiType));
        return apiInfo.getApiCode();
    }

}
