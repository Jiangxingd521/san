package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.RoleBarMenuCommand;
import com.ningyang.os.dao.SysRoleMenuInfoMapper;
import com.ningyang.os.pojo.SysRoleMenuInfo;
import com.ningyang.os.service.ISysRoleMenuInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色关联菜单 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@Service
public class SysRoleMenuInfoServiceImpl extends ServiceImpl<SysRoleMenuInfoMapper, SysRoleMenuInfo> implements ISysRoleMenuInfoService {

    @Override
    public List<Long> findRoleMenuIdList(Long roleId) {
        List<Long> menuList = new ArrayList<>();
        List<SysRoleMenuInfo> list = list(new QueryWrapper<SysRoleMenuInfo>().eq("role_id", roleId));
        for (SysRoleMenuInfo menuInfo : list) {
            menuList.add(menuInfo.getMenuId());
        }
        return menuList;
    }

    @Override
    public boolean roleMenu(RoleBarMenuCommand command) {
        remove(new QueryWrapper<SysRoleMenuInfo>().eq("role_id", command.getRoleId()));
        List<SysRoleMenuInfo> list = new ArrayList<>();
        for (Long menuId : command.getIds()) {
            SysRoleMenuInfo roleBarInfo = new SysRoleMenuInfo();
            roleBarInfo.setRoleId(command.getRoleId());
            roleBarInfo.setMenuId(menuId);
            list.add(roleBarInfo);
        }
        return saveBatch(list);
    }

}
