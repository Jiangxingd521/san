package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberPointRuleVo;
import com.ningyang.os.pojo.MemberPointRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 会员积分规则 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface MemberPointRuleMapper extends BaseMapper<MemberPointRule> {
    List<MemberPointRuleVo> selectMemberPointRuleVoListByCondition(QueryMemberCondition condition);
    Integer getListfindName(String ruleName,Long ruleId);
    Integer deletePointRuleById(Long RuleId);
}
