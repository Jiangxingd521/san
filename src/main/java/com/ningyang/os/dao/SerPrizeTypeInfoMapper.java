package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeTypeVo;
import com.ningyang.os.pojo.SerPrizeTypeInfo;

import java.util.List;

/**
 * <p>
 * 奖项类型 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface SerPrizeTypeInfoMapper extends BaseMapper<SerPrizeTypeInfo> {

    List<PrizeTypeVo> selectPrizeTypeVoPageByCondition(QueryPrizeCondition condition);
    List<PrizeTypeVo> selectPrizeTypeVoListByCondition(QueryPrizeCondition condition);
    Long selectPrizeTypeVoListByConditionCount(QueryPrizeCondition condition);
}
