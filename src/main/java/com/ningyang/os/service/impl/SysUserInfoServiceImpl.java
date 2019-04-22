package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.config.SystemConfig;
import com.ningyang.os.action.input.command.web.base.UserCommand;
import com.ningyang.os.action.input.command.web.base.UserPwdCommand;
import com.ningyang.os.action.input.condition.base.QueryUserCondition;
import com.ningyang.os.action.output.dto.web.UserInfoDto;
import com.ningyang.os.action.output.dto.web.UserRoleDto;
import com.ningyang.os.action.output.vo.web.base.SysUserVo;
import com.ningyang.os.dao.SysMenuInfoMapper;
import com.ningyang.os.dao.SysUserInfoMapper;
import com.ningyang.os.dao.SysUserRoleInfoMapper;
import com.ningyang.os.pojo.SysUserInfo;
import com.ningyang.os.service.ISysUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements ISysUserInfoService {
    @Autowired
    SysUserRoleInfoMapper userRoleMapper;
    @Autowired
    SysMenuInfoMapper menuInfoMapper;
    @Autowired
    private SystemConfig config;

    @Override
    public SysUserInfo findByCondition(QueryUserCondition condition) {
        return baseMapper.selectByCondition(condition);
    }

    @Override
    public UserInfoDto getUserInfoDto(String userId) {
        UserInfoDto dto = new UserInfoDto();
        //用户基础信息
        SysUserInfo userInfo = getById(userId);
        dto.setUsername(userInfo.getUserName());
        dto.setName(userInfo.getLoginName());
        //权限
        List<String> roles = new ArrayList<>();
        List<UserRoleDto> roleList = userRoleMapper.selectByUserId(userId);
        for (UserRoleDto roleDto : roleList) {
            String role = String.valueOf(roleDto.getRoleId());
            roles.add(role);
        }
        dto.setRoles(roles);
        //菜单按钮标识
        List<String> permission = menuInfoMapper.selectByUserId(userId);
        dto.setPermission(permission);
        return dto;
    }

    @Override
    public Page<SysUserVo> findSysUserVoPageByCondition(QueryUserCondition condition) {
        Page<SysUserVo> pageVo = new Page<>();
        if (condition.getPid().equals("1")) {
            condition.setUserFlag(1);
        } else {
            condition.setUserFlag(0);
        }
        List<SysUserVo> listVoTemp = baseMapper.selectSysUserVoPageByCondition(condition);
        long total = baseMapper.selectSysUserVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean add(UserCommand command) {
//        System.out.println(config.getDefaultPassword());
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setPid(command.getPid());
        userInfo.setLoginName(command.getLoginName());
        userInfo.setUserName(command.getUserName());
        userInfo.setUserType(command.getUserType());
        userInfo.setUserState(0);
        String encodePassword = DigestUtils.md5DigestAsHex((config.getDefaultPassword()).getBytes());
        userInfo.setLoginPassword(encodePassword);
        userInfo.setLoginPasswordPlaintext(config.getDefaultPassword());
        userInfo.setCreateUserId(Long.valueOf(command.getPid()));
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());
        return save(userInfo);
    }

    @Override
    public boolean edit(UserCommand command) {
        SysUserInfo userInfo = getById(command.getUserId());
        userInfo.setUserName(command.getUserName());
        userInfo.setUserState(command.getUserState());
        if (command.isPassword()) {
            String encodePassword = DigestUtils.md5DigestAsHex((config.getDefaultPassword()).getBytes());
            userInfo.setLoginPassword(encodePassword);
            userInfo.setLoginPasswordPlaintext(config.getDefaultPassword());
        }
        userInfo.setCreateUserId(Long.valueOf(command.getPid()));
        userInfo.setUpdateTime(new Date());
        return updateById(userInfo);
    }

    @Override
    public boolean updateUserPassword(UserPwdCommand command) {
        SysUserInfo userInfo = getById(command.getUserId());
        String encodePassword = DigestUtils.md5DigestAsHex((command.getUserPassword()).getBytes());
        userInfo.setLoginPassword(encodePassword);
        userInfo.setLoginPasswordPlaintext(command.getUserPassword());
        userInfo.setUpdateTime(new Date());
        return updateById(userInfo);
    }
}
