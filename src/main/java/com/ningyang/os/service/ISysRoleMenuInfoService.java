package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.RoleBarMenuCommand;
import com.ningyang.os.pojo.SysRoleMenuInfo;

import java.util.List;

/**
 * <p>
 * 角色关联菜单 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface ISysRoleMenuInfoService extends IService<SysRoleMenuInfo> {

    List<Long> findRoleMenuIdList(Long roleId);

    boolean roleMenu(RoleBarMenuCommand command);

}
