package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.base.QueryUserCondition;
import com.ningyang.os.action.output.vo.web.base.SysUserVo;
import com.ningyang.os.pojo.SysUserInfo;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface SysUserInfoMapper extends BaseMapper<SysUserInfo> {

    SysUserInfo selectByCondition(QueryUserCondition condition);

    List<SysUserVo> selectSysUserVoPageByCondition(QueryUserCondition condition);

    Long selectSysUserVoPageCountByCondition(QueryUserCondition condition);

}
