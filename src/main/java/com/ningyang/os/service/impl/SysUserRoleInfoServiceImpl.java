package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.UserRoleCommand;
import com.ningyang.os.dao.SysUserRoleInfoMapper;
import com.ningyang.os.pojo.SysUserRoleInfo;
import com.ningyang.os.service.ISysUserRoleInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户关联角色 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@Service
public class SysUserRoleInfoServiceImpl extends ServiceImpl<SysUserRoleInfoMapper, SysUserRoleInfo> implements ISysUserRoleInfoService {

    @Override
    public List<Long> findUserRoleIdList(Long userId) {
        List<Long> roleList = new ArrayList<>();
        List<SysUserRoleInfo> list = list(new QueryWrapper<SysUserRoleInfo>().eq("user_id", userId));
        for (SysUserRoleInfo roleInfo : list) {
            roleList.add(roleInfo.getRoleId());
        }
        return roleList;
    }

    @Override
    public boolean userRole(UserRoleCommand command) {
        remove(new QueryWrapper<SysUserRoleInfo>().eq("user_id", command.getUserId()));
        List<SysUserRoleInfo> list = new ArrayList<>();
        for (Long roleId : command.getIds()) {
            SysUserRoleInfo userRoleInfo = new SysUserRoleInfo();
            userRoleInfo.setUserId(command.getUserId());
            userRoleInfo.setRoleId(roleId);
            list.add(userRoleInfo);
        }
        return saveBatch(list);
    }
}
