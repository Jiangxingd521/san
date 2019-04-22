package com.ningyang.os.controller.system.login;

import com.ningyang.os.action.input.condition.base.QueryUserCondition;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.pojo.SysUserInfo;
import com.ningyang.os.service.ISysNavigationBarInfoService;
import com.ningyang.os.service.ISysUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.PASSWORD_ERROR;
import static com.ningyang.os.action.utils.JwtUtil.buildJWT;

/**
 * @Author： kaider
 * @Date：2018/09/27 14:27
 * @描述：用户登录
 */
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ISysUserInfoService infoService;
    @Autowired
    private ISysNavigationBarInfoService barInfoService;

    /**
     * 系统用户登录
     *
     * @param condition
     * @return
     */
    @PostMapping("login")
    public Map<String, Object> login(
            QueryUserCondition condition
    ) {
        try {
            String encodePassword = DigestUtils.md5DigestAsHex((condition.getPassword()).getBytes());
            condition.setPassword(encodePassword);
            SysUserInfo userInfo = infoService.findByCondition(condition);
            if (userInfo != null) {
                String token = buildJWT(String.valueOf(userInfo.getId()));
                //用户的默认引导页
                String tagWel = barInfoService.findUserWelTagByUserId(userInfo.getId());
                return WebResult.success().put("token", token).put("tagWel", tagWel).toMap();
            }
            return WebResult.failure(PASSWORD_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }
}
