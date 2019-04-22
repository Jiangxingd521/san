package com.ningyang.os.controller.system.menu;

import com.ningyang.os.action.input.command.web.base.BarCommand;
import com.ningyang.os.action.input.command.web.base.RoleBarMenuCommand;
import com.ningyang.os.action.input.condition.base.QueryMenuCondition;
import com.ningyang.os.action.output.vo.web.base.BarVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.pojo.SysUserInfo;
import com.ningyang.os.service.ISysNavigationBarInfoService;
import com.ningyang.os.service.ISysNavigationBarMenuInfoService;
import com.ningyang.os.service.ISysRoleNavigationBarInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.*;

/**
 * @Author： kaider
 * @Date：2018/09/28 16:44
 * @描述：导航栏管理
 */
@RestController
@RequestMapping("sys/navigationBar")
public class NavigationBarController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationBarController.class);

    @Autowired
    private ISysNavigationBarInfoService barInfoService;
    @Autowired
    private ISysRoleNavigationBarInfoService roleBarInfoService;
    @Autowired
    private ISysNavigationBarMenuInfoService roleMenuInfoService;

    /**
     * 获取导航栏菜单列表
     *
     * @param condition
     * @return
     */
    @GetMapping("getNavigationBarList")
    public Map<String, Object> getNavigationBarList(
            QueryMenuCondition condition
    ) {
        try {
            List<BarVo> listVo = barInfoService.findBarVoListByCondition(condition);
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
    @PostMapping("add")
    public Map<String, Object> add(
            @RequestBody BarCommand command
    ) {
        try {
            boolean flag = barInfoService.add(command);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(ADD_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(ADD_ERROR.getInfo()).toMap();
        }
    }

    /**
     * @param command
     * @return
     */
    @PutMapping("edit")
    public Map<String, Object> edit(
            @RequestBody BarCommand command
    ) {
        try {
            boolean flag = barInfoService.edit(command);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(EDIT_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(EDIT_ERROR.getInfo()).toMap();
        }
    }

    /**
     * 用户登录获取对应的导航栏菜单列表
     *
     * @param userToken
     * @param condition
     * @return
     */
    @GetMapping("getUserBarList")
    public Map<String, Object> getUserBarList(
            @RequestHeader("Authorization") String userToken,
            QueryMenuCondition condition
    ) {
        try {
            SysUserInfo userInfo = getBaseUserInfo(userToken);
            condition.setUserId(String.valueOf(userInfo.getId()));
            List<BarVo> listVo = barInfoService.findUserBarVoListByCondition(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * 获取角色授权的导航栏id
     *
     * @param roleId
     * @return
     */
    @GetMapping("getRoleBarList/{roleId}")
    public Map<String, Object> getRoleBarList(
            @PathVariable("roleId") Long roleId
    ) {
        try {
            List<Long> listVo = roleBarInfoService.findRoleBarIdList(roleId);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * 授权导航栏
     *
     * @param command
     * @return
     */
    @PostMapping("roleBar")
    public Map<String, Object> roleBar(
            @RequestBody RoleBarMenuCommand command
    ) {
        try {
            boolean flag = roleBarInfoService.roleBar(command);
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
     * 获取授权菜单id
     *
     * @param barId
     * @return
     */
    @GetMapping("getRoleMenuList/{barId}")
    public Map<String, Object> getRoleMenuList(
            @PathVariable("barId") Long barId
    ) {
        try {
            List<Long> listVo = roleMenuInfoService.findRoleMenuIdList(barId);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * 授权菜单
     *
     * @param command
     * @return
     */
    @PostMapping("roleMenu")
    public Map<String, Object> roleMenu(
            @RequestBody RoleBarMenuCommand command
    ) {
        try {
            boolean flag = roleMenuInfoService.roleMenu(command);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(OPERATING_PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_PERMISSION_ERROR.getInfo()).toMap();
        }
    }

}
