package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.MemberTypeCommand;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberTypeVo;
import com.ningyang.os.pojo.MemberTypeInfo;

import java.util.List;

/**
 * <p>
 * 会员类型 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface IMemberTypeInfoService extends IService<MemberTypeInfo> {

    List<MemberTypeVo> findMemberTypeVoListByCondition(QueryMemberCondition condition);

    boolean addOrUpdate(MemberTypeCommand command, Long userId);

    boolean deleteMemberType(Long typeId);
}
