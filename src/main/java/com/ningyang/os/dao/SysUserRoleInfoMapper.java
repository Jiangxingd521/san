package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.output.dto.web.UserRoleDto;
import com.ningyang.os.pojo.SysUserRoleInfo;

import java.util.List;

/**
 * <p>
 * 用户关联角色 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface SysUserRoleInfoMapper extends BaseMapper<SysUserRoleInfo> {

    List<UserRoleDto> selectByUserId(String userId);

}
