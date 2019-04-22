package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeManagerVo;
import com.ningyang.os.pojo.SerPrizeManagerInfo;

import java.util.List;

/**
 * <p>
 * 奖项管理 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface SerPrizeManagerInfoMapper extends BaseMapper<SerPrizeManagerInfo> {

    List<PrizeManagerVo> selectPrizeManagerVoListByCondition(QueryPrizeCondition condition);
    List<PrizeManagerVo> selectPrizeManagerVoPageByCondition(QueryPrizeCondition condition);
    Long selectPrizeManagerVoPageByConditionCount(QueryPrizeCondition condition);
    PrizeManagerVo selectPrizeManagerVoById(Long Id);
}
