package com.ningyang.os.controller.system.menu;

import com.ningyang.os.action.input.command.web.base.MenuCommand;
import com.ningyang.os.action.input.command.web.base.RoleBarMenuCommand;
import com.ningyang.os.action.input.condition.base.QueryMenuCondition;
import com.ningyang.os.action.output.vo.web.base.BarMenuVo;
import com.ningyang.os.action.output.vo.web.base.SysMenuVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.pojo.SysUserInfo;
import com.ningyang.os.service.ISysMenuInfoService;
import com.ningyang.os.service.ISysRoleMenuInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.*;

/**
 * @Author： kaider
 * @Date：2018/09/28 16:43
 * @描述：菜单管理
 */
@RestController
@RequestMapping("sys/menu")
public class MenuController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private ISysMenuInfoService menuInfoService;
    @Autowired
    private ISysRoleMenuInfoService roleMenuInfoService;

    /**
     * 获取用户导航栏对应的菜单列表
     *
     * @param userToken
     * @param condition
     * @return
     */
    @GetMapping("getUserBarMenuList/{barId}")
    public Map<String, Object> getUserBarMenuList(
            @RequestHeader("Authorization") String userToken,
            QueryMenuCondition condition
    ) {
        try {
            SysUserInfo userInfo = getBaseUserInfo(userToken);
            condition.setUserId(String.valueOf(userInfo.getId()));
            List<BarMenuVo> listVo = menuInfoService.findUserBarMenuVoList(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * 获取菜单树
     *
     * @return
     */
    @GetMapping("getMenuTree")
    public Map<String, Object> getMenuTree(
            QueryMenuCondition condition
    ) {
        try {
            List<SysMenuVo> listVo = menuInfoService.findSysMenuVoList(condition);
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
            @RequestBody MenuCommand command
    ) {
        try {
            boolean flag = menuInfoService.add(command);
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
            @RequestBody MenuCommand command
    ) {
        try {
            boolean flag = menuInfoService.edit(command);
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
     * @param menuId
     * @return
     */
    @DeleteMapping("delete/{menuId}")
    public Map<String, Object> delete(
            @PathVariable("menuId") Long menuId
    ) {
        try {
            boolean flag = menuInfoService.delete(menuId);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(DELETE_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DELETE_ERROR.getInfo()).toMap();
        }
    }

    /**
     * 获取角色授权的菜单id
     *
     * @param roleId
     * @return
     */
    @GetMapping("getRoleMenuList/{roleId}")
    public Map<String, Object> getRoleMenuList(
            @PathVariable("roleId") Long roleId
    ) {
        try {
            List<Long> listVo = roleMenuInfoService.findRoleMenuIdList(roleId);
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
