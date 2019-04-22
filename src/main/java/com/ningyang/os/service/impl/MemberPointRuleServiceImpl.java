package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.MemberPointRuleCommand;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberPointRuleVo;
import com.ningyang.os.dao.MemberPointRuleMapper;
import com.ningyang.os.pojo.MemberPointRule;
import com.ningyang.os.service.IMemberPointRuleService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员积分规则 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@Service
public class MemberPointRuleServiceImpl extends ServiceImpl<MemberPointRuleMapper, MemberPointRule> implements IMemberPointRuleService {

    @Override
    public List<MemberPointRuleVo> findMemberPointRuleVoListByCondition(QueryMemberCondition condition) {
        return baseMapper.selectMemberPointRuleVoListByCondition(condition);
    }

    @Override
    public boolean addOrUpdate(MemberPointRuleCommand command, Long userId) {
        MemberPointRule info = getById(command.getRuleId());
        boolean flag;
        if (info != null) {
            info.setRuleType(command.getRuleName());
            info.setRuleValue(command.getRuleValue());
            info.setIdata1(command.getRuleState());
            info.setUserId(userId);
            info.setUpdateTime(new Date());
            flag = updateById(info);
        } else {
            info = new MemberPointRule();
            info.setRuleType(command.getRuleName());
            info.setRuleValue(command.getRuleValue());
            info.setIdata1(0);
            //删除标识 0为未删除 1为已删除
            info.setSdata1(0);
            info.setUserId(userId);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag = save(info);
        }
        return flag;
    }

    @Override
    public boolean getListfindName(String ruleName,Long ruleId) {
        int i=baseMapper.getListfindName(ruleName,ruleId);
        if (ruleId!=0){
            return i!=1;
        }
            return i>0;
    }

    @Override
    public boolean deletePointRuleById(Long ruleId) {
        int i=baseMapper.deletePointRuleById(ruleId);

        return i>0;
    }

}
