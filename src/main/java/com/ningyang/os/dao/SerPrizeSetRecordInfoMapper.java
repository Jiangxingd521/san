package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetRecordVo;
import com.ningyang.os.pojo.SerPrizeSetRecordInfo;

import java.util.List;

/**
 * <p>
 * 奖项设定操作记录 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-12-24
 */
public interface SerPrizeSetRecordInfoMapper extends BaseMapper<SerPrizeSetRecordInfo> {

    List<PrizeSetRecordVo> selectPrizeSetRecordVoPageByCondition(QueryPrizeCondition condition);

    Long selectPrizeSetRecordVoPageCountByCondition(QueryPrizeCondition condition);

}
