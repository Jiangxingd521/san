package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetVo;
import com.ningyang.os.pojo.SerPrizeSetInfo;

import java.util.List;

/**
 * <p>
 * 奖项设定 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface SerPrizeSetInfoMapper extends BaseMapper<SerPrizeSetInfo> {

    List<PrizeSetVo> selectPrizeSetVoListByCondition(QueryPrizeCondition condition);

    List<PrizeSetVo> selectPrizeSetVoListPageByCondition(QueryPrizeCondition condition);

    Long selectPrizeSetVoListPageCountByCondition(QueryPrizeCondition condition);

    PrizeSetVo selectPrizeSetVoById(Long prizeSetId);
}
