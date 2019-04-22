package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.base.QueryRoleCondition;
import com.ningyang.os.action.output.vo.web.base.SysRoleVo;
import com.ningyang.os.pojo.SysRoleInfo;

import java.util.List;

/**
 * <p>
 * 角色权限表 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface SysRoleInfoMapper extends BaseMapper<SysRoleInfo> {

    List<SysRoleVo> selectRoleVoPageByCondition(QueryRoleCondition condition);

    Long selectRoleVoPageCountByCondition(QueryRoleCondition condition);

    List<SysRoleVo> selectRoleVoByCondition(QueryRoleCondition condition);

}
