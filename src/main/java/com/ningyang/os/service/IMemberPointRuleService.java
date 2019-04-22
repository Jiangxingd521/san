package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.MemberPointRuleCommand;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberPointRuleVo;
import com.ningyang.os.pojo.MemberPointRule;

import java.util.List;

/**
 * <p>
 * 会员积分规则 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface IMemberPointRuleService extends IService<MemberPointRule> {

    List<MemberPointRuleVo> findMemberPointRuleVoListByCondition(QueryMemberCondition condition);

    boolean addOrUpdate(MemberPointRuleCommand command, Long userId);

    boolean getListfindName(String ruleName,Long ruleId);

    boolean deletePointRuleById(Long ruleId);
}
