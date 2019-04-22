package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberInfoVo;
import com.ningyang.os.pojo.MemberInfo;

/**
 * <p>
 * 会员数据 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface IMemberInfoService extends IService<MemberInfo> {

    Page<MemberInfoVo> findMemberInfoVoPageByCondition(QueryMemberCondition condition);

}
