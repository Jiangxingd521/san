package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberInfoVo;
import com.ningyang.os.pojo.MemberInfo;

import java.util.List;

/**
 * <p>
 * 会员数据 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface MemberInfoMapper extends BaseMapper<MemberInfo> {

    List<MemberInfoVo> selectMemberInfoVoPageByCondition(QueryMemberCondition condition);

    Long selectMemberInfoVoPageCountByCondition(QueryMemberCondition condition);

}
