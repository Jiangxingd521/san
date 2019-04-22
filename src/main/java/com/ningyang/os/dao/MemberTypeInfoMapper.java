package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberTypeVo;
import com.ningyang.os.pojo.MemberTypeInfo;

import java.util.List;

/**
 * <p>
 * 会员类型 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface MemberTypeInfoMapper extends BaseMapper<MemberTypeInfo> {

    List<MemberTypeVo> selectMemberTypeVoListByCondition(QueryMemberCondition condition);

    Integer deleteMemberType(Long typeId);
}
