package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.RoleBarMenuCommand;
import com.ningyang.os.dao.SysNavigationBarMenuInfoMapper;
import com.ningyang.os.pojo.SysNavigationBarMenuInfo;
import com.ningyang.os.service.ISysNavigationBarMenuInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单关联导航 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@Service
public class SysNavigationBarMenuInfoServiceImpl extends ServiceImpl<SysNavigationBarMenuInfoMapper, SysNavigationBarMenuInfo> implements ISysNavigationBarMenuInfoService {

    @Override
    public List<Long> findRoleMenuIdList(Long barId) {
        List<Long> menuList = new ArrayList<>();
        List<SysNavigationBarMenuInfo> list = list(new QueryWrapper<SysNavigationBarMenuInfo>().eq("bar_id", barId));
        for (SysNavigationBarMenuInfo barInfo : list) {
            menuList.add(barInfo.getMenuId());
        }
        return menuList;
    }

    @Override
    public boolean roleMenu(RoleBarMenuCommand command) {
        remove(new QueryWrapper<SysNavigationBarMenuInfo>().eq("bar_id", command.getRoleId()));
        List<SysNavigationBarMenuInfo> list = new ArrayList<>();
        for (Long menuId : command.getIds()) {
            SysNavigationBarMenuInfo roleBarInfo = new SysNavigationBarMenuInfo();
            roleBarInfo.setBarId(command.getRoleId());
            roleBarInfo.setMenuId(menuId);
            list.add(roleBarInfo);
        }
        return saveBatch(list);
    }
}
