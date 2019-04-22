package com.ningyang.os.controller.system.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.base.UserCommand;
import com.ningyang.os.action.input.command.web.base.UserPwdCommand;
import com.ningyang.os.action.input.command.web.base.UserRoleCommand;
import com.ningyang.os.action.input.condition.base.QueryUserCondition;
import com.ningyang.os.action.output.dto.web.UserInfoDto;
import com.ningyang.os.action.output.vo.web.base.SysUserVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.pojo.SysUserInfo;
import com.ningyang.os.service.ISysUserInfoService;
import com.ningyang.os.service.ISysUserRoleInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.*;
import static com.ningyang.os.action.utils.JwtUtil.parseJWTToString;

/**
 * @Author： kaider
 * @Date：2018/10/03 13:27
 * @描述：用户信息管理
 */
@RestController
@RequestMapping("sys/user")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ISysUserInfoService infoService;
    @Autowired
    private ISysUserRoleInfoService userRoleInfoService;

    /**
     * 获取登录用户信息
     *
     * @param userToken
     * @return
     */
    @GetMapping("getLoginUserInfo")
    public Map<String, Object> getLoginUserInfo(
            @RequestHeader("Authorization") String userToken
    ) {
        try {
            String userId = parseJWTToString(userToken);
            UserInfoDto userDto = infoService.getUserInfoDto(userId);
            if (userDto != null) {
                return WebResult.success().put("userData", userDto).toMap();
            }
            return WebResult.failure(LOGIN_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * @param userToken
     * @param condition
     * @return
     */
    @GetMapping("getPageList")
    public Map<String, Object> getPageList(
            @RequestHeader("Authorization") String userToken,
            QueryUserCondition condition
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            condition.setPid(loginUser.getId().toString());
            Page<SysUserVo> pageVo = infoService.findSysUserVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * @param userToken
     * @param command
     * @return
     */
    @PostMapping("add")
    public Map<String, Object> add(
            @RequestHeader("Authorization") String userToken,
            @RequestBody UserCommand command
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                command.setPid(loginUser.getId());
                boolean flag = infoService.add(command);
                if (flag) {
                    return WebResult.success().toMap();
                }
                return WebResult.failure(ADD_ERROR.getInfo()).toMap();
            }
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * @param userToken
     * @param command
     * @return
     */
    @PutMapping("edit")
    public Map<String, Object> edit(
            @RequestHeader("Authorization") String userToken,
            @RequestBody UserCommand command
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                command.setPid(loginUser.getId());
                boolean flag = infoService.edit(command);
                if (flag) {
                    return WebResult.success().toMap();
                }
                return WebResult.failure(EDIT_ERROR.getInfo()).toMap();
            }
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("getUserRoleList/{userId}")
    public Map<String, Object> getRoleUserList(
            @PathVariable("userId") Long userId
    ) {
        try {
            List<Long> listVo = userRoleInfoService.findUserRoleIdList(userId);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * @param command
     * @return
     */
    @PostMapping("userRole")
    public Map<String, Object> userRole(
            @RequestBody UserRoleCommand command
    ) {
        try {
            boolean flag = userRoleInfoService.userRole(command);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(OPERATING_PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_PERMISSION_ERROR.getInfo()).toMap();
        }
    }

    /**
     * @param userToken
     * @param command
     * @return
     */
    @PutMapping("updatePassword")
    public Map<String, Object> updatePassword(
            @RequestHeader("Authorization") String userToken,
            @RequestBody UserPwdCommand command
    ) {
        try {
            String loginUserId = parseJWTToString(userToken);
            command.setUserId(loginUserId);
            boolean flag = infoService.updateUserPassword(command);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(EDIT_PASSWORD_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

}
