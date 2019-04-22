package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.RoleBarMenuCommand;
import com.ningyang.os.dao.SysRoleNavigationBarInfoMapper;
import com.ningyang.os.pojo.SysRoleNavigationBarInfo;
import com.ningyang.os.service.ISysRoleNavigationBarInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色关联导航栏 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@Service
public class SysRoleNavigationBarInfoServiceImpl extends ServiceImpl<SysRoleNavigationBarInfoMapper, SysRoleNavigationBarInfo> implements ISysRoleNavigationBarInfoService {

    @Override
    public List<Long> findRoleBarIdList(Long roleId) {
        List<Long> barList = new ArrayList<>();
        List<SysRoleNavigationBarInfo> list = list(new QueryWrapper<SysRoleNavigationBarInfo>().eq("role_id", roleId));
        for (SysRoleNavigationBarInfo barInfo : list) {
            barList.add(barInfo.getBarId());
        }
        return barList;
    }

    @Override
    public boolean roleBar(RoleBarMenuCommand command) {
        remove(new QueryWrapper<SysRoleNavigationBarInfo>().eq("role_id", command.getRoleId()));
        List<SysRoleNavigationBarInfo> list = new ArrayList<>();
        for (Long barId : command.getIds()) {
            SysRoleNavigationBarInfo roleBarInfo = new SysRoleNavigationBarInfo();
            roleBarInfo.setRoleId(command.getRoleId());
            roleBarInfo.setBarId(barId);
            list.add(roleBarInfo);
        }
        return saveBatch(list);
    }

}
