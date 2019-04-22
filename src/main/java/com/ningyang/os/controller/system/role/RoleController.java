package com.ningyang.os.controller.system.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.base.RoleCommand;
import com.ningyang.os.action.input.condition.base.QueryRoleCondition;
import com.ningyang.os.action.output.vo.web.base.SysRoleVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.service.ISysRoleInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.ADD_ERROR;
import static com.ningyang.os.action.enums.SystemErrorEnum.EDIT_ERROR;

/**
 * @Author： kaider
 * @Date：2018/10/02 12:48
 * @描述：角色管理
 */
@RestController
@RequestMapping("sys/role")
public class RoleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private ISysRoleInfoService roleInfoService;

    /**
     * @param condition
     * @return
     */
    @GetMapping("getPageList")
    public Map<String, Object> getPageList(
            QueryRoleCondition condition
    ) {
        try {
            Page<SysRoleVo> pageVo = roleInfoService.findRoleVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
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
            @RequestBody RoleCommand command
    ) {
        try {
            boolean flag = roleInfoService.add(command);
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
            @RequestBody RoleCommand command
    ) {
        try {
            boolean flag = roleInfoService.edit(command);
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
     * @param condition
     * @return
     */
    @GetMapping("getRoleList")
    public Map<String, Object> getRoleList(
            QueryRoleCondition condition
    ) {
        try {
            List<SysRoleVo> listVo = roleInfoService.findRoleVoByCondition(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

}
