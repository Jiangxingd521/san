package com.ningyang.os.controller.api;

import com.ningyang.os.action.input.condition.base.QueryUserCondition;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.pojo.SysUserInfo;
import com.ningyang.os.service.ISysUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.API_REQUEST_ERROR;
import static com.ningyang.os.action.enums.SystemErrorEnum.PASSWORD_ERROR;
import static com.ningyang.os.action.utils.JwtUtil.buildJWT;

/**
 * @Author： kaider
 * @Date：2018/11/27 09:31
 * @描述：
 */
@Api(tags = {"登录模块"})
@RestController
@RequestMapping("api")
public class ApiLoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiLoginController.class);

    @Autowired
    private ISysUserInfoService userInfoService;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query")
    })
    @PostMapping("login")
    public Map<String, Object> login(
            String username,
            String password
    ) {
        try {
            String encodePassword = DigestUtils.md5DigestAsHex((password).getBytes());
            QueryUserCondition condition = new QueryUserCondition();
            condition.setUsername(username);
            condition.setPassword(encodePassword);
            condition.setUserType(0);
            SysUserInfo userInfo = userInfoService.findByCondition(condition);
            if (userInfo != null) {
                String token = buildJWT(String.valueOf(userInfo.getId()));
                return WebResult.success().put("data", token).toMap();
            }
            return WebResult.failure(PASSWORD_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }
}
