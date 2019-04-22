package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.MemberTypeCommand;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberTypeVo;
import com.ningyang.os.dao.MemberTypeInfoMapper;
import com.ningyang.os.pojo.MemberTypeInfo;
import com.ningyang.os.service.IMemberTypeInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员类型 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@Service
public class MemberTypeInfoServiceImpl extends ServiceImpl<MemberTypeInfoMapper, MemberTypeInfo> implements IMemberTypeInfoService {

    @Override
    public List<MemberTypeVo> findMemberTypeVoListByCondition(QueryMemberCondition condition) {
        return baseMapper.selectMemberTypeVoListByCondition(condition);
    }

    @Override
    public boolean addOrUpdate(MemberTypeCommand command, Long userId) {
        MemberTypeInfo info = getById(command.getTypeId());
        boolean flag;
        if (info != null) {
            info.setTypeName(command.getTypeName());
            info.setTypeRuleId(command.getRuleId());
            info.setTypeQuanty(command.getTypeQuanty());
            info.setUserId(userId);
            info.setIdata1(command.getTypeState());
            info.setUpdateTime(new Date());
            flag = updateById(info);
        } else {
            info = new MemberTypeInfo();
            info.setTypeName(command.getTypeName());
            info.setTypeRuleId(command.getRuleId());
            info.setTypeQuanty(command.getTypeQuanty());
            info.setUserId(userId);
            info.setIdata1(0);
            //删除标记  0为可用 1为不可用
            info.setIdata2(0);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag = save(info);
        }
        return flag;
    }

    @Override
    public boolean deleteMemberType(Long typeId) {
        return baseMapper.deleteMemberType(typeId)>0;
    }
}
