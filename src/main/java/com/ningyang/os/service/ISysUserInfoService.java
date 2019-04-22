package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.UserCommand;
import com.ningyang.os.action.input.command.web.base.UserPwdCommand;
import com.ningyang.os.action.input.condition.base.QueryUserCondition;
import com.ningyang.os.action.output.dto.web.UserInfoDto;
import com.ningyang.os.action.output.vo.web.base.SysUserVo;
import com.ningyang.os.pojo.SysUserInfo;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface ISysUserInfoService extends IService<SysUserInfo> {

    SysUserInfo findByCondition(QueryUserCondition condition);

    UserInfoDto getUserInfoDto(String userId);

    Page<SysUserVo> findSysUserVoPageByCondition(QueryUserCondition condition);

    boolean add(UserCommand command);

    boolean edit(UserCommand command);

    boolean updateUserPassword(UserPwdCommand command);

}
