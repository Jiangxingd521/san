package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.UserRoleCommand;
import com.ningyang.os.pojo.SysUserRoleInfo;

import java.util.List;

/**
 * <p>
 * 用户关联角色 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface ISysUserRoleInfoService extends IService<SysUserRoleInfo> {

    List<Long> findUserRoleIdList(Long userId);

    boolean userRole(UserRoleCommand command);

}
