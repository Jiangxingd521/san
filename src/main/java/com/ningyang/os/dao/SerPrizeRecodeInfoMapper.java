package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetLogVo;
import com.ningyang.os.action.output.vo.web.serve.PrizeTicketLogVo;
import com.ningyang.os.pojo.SerPrizeRecodeInfo;

import java.util.List;

/**
 * <p>
 * 布奖记录 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface SerPrizeRecodeInfoMapper extends BaseMapper<SerPrizeRecodeInfo> {

    List<PrizeSetLogVo> selectPrizeSetLogVoPageByCondition(QueryPrizeCondition condition);

    Long selectPrizeSetLogVoPageCountByCondition(QueryPrizeCondition condition);

    boolean insertBatch(List<SerPrizeRecodeInfo> listData);

    List<PrizeTicketLogVo> selectPrizeTicketLogVoPageByCondition(QueryPrizeCondition condition);

    Long selectPrizeTicketLogVoPageCountByCondition(QueryPrizeCondition condition);
}
