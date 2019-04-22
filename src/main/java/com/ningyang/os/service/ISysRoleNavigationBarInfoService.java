package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.RoleBarMenuCommand;
import com.ningyang.os.pojo.SysRoleNavigationBarInfo;

import java.util.List;

/**
 * <p>
 * 角色关联导航栏 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface ISysRoleNavigationBarInfoService extends IService<SysRoleNavigationBarInfo> {

    List<Long> findRoleBarIdList(Long roleId);

    boolean roleBar(RoleBarMenuCommand command);

}
