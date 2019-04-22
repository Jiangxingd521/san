package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.RoleCommand;
import com.ningyang.os.action.input.condition.base.QueryRoleCondition;
import com.ningyang.os.action.output.vo.web.base.SysRoleVo;
import com.ningyang.os.pojo.SysRoleInfo;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface ISysRoleInfoService extends IService<SysRoleInfo> {

    Page<SysRoleVo> findRoleVoPageByCondition(QueryRoleCondition condition);

    boolean add(RoleCommand command);

    boolean edit(RoleCommand command);

    List<SysRoleVo> findRoleVoByCondition(QueryRoleCondition condition);

}
