package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.RoleBarMenuCommand;
import com.ningyang.os.pojo.SysNavigationBarMenuInfo;

import java.util.List;

/**
 * <p>
 * 菜单关联导航 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface ISysNavigationBarMenuInfoService extends IService<SysNavigationBarMenuInfo> {

    List<Long> findRoleMenuIdList(Long barId);

    boolean roleMenu(RoleBarMenuCommand command);

}
