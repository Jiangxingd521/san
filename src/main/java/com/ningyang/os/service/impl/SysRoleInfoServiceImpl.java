package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.RoleCommand;
import com.ningyang.os.action.input.condition.base.QueryRoleCondition;
import com.ningyang.os.action.output.vo.web.base.SysRoleVo;
import com.ningyang.os.dao.SysRoleInfoMapper;
import com.ningyang.os.pojo.SysRoleInfo;
import com.ningyang.os.service.ISysRoleInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@Service
public class SysRoleInfoServiceImpl extends ServiceImpl<SysRoleInfoMapper, SysRoleInfo> implements ISysRoleInfoService {

    @Override
    public Page<SysRoleVo> findRoleVoPageByCondition(QueryRoleCondition condition) {
        Page<SysRoleVo> pageVo = new Page<>();
        List<SysRoleVo> listVoTemp = baseMapper.selectRoleVoPageByCondition(condition);
        long total = baseMapper.selectRoleVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean add(RoleCommand command) {
        SysRoleInfo roleInfo = new SysRoleInfo();
        roleInfo.setRoleName(command.getRoleName());
        roleInfo.setRoleFlag(0);
        roleInfo.setRoleState(0);
        roleInfo.setCreateTime(new Date());
        roleInfo.setUpdateTime(new Date());
        return save(roleInfo);
    }

    @Override
    public boolean edit(RoleCommand command) {
        SysRoleInfo roleInfo = getById(command.getRoleId());
        roleInfo.setRoleName(command.getRoleName());
        roleInfo.setRoleState(command.getRoleState());
        roleInfo.setUpdateTime(new Date());
        return updateById(roleInfo);
    }

    @Override
    public List<SysRoleVo> findRoleVoByCondition(QueryRoleCondition condition) {
        return baseMapper.selectRoleVoByCondition(condition);
    }
}
