package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryDealerCondition;
import com.ningyang.os.action.output.vo.web.serve.DealerVo;
import com.ningyang.os.pojo.SerDealerInfo;

import java.util.List;

/**
 * <p>
 * 供应商信息 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface SerDealerInfoMapper extends BaseMapper<SerDealerInfo> {

    List<DealerVo> selectDealerVoPageByCondition(QueryDealerCondition condition);

    Long selectDealerVoPageCountByCondition(QueryDealerCondition condition);

    List<DealerVo> selectDealerVoListByCondition();

    Integer isOrderInformation(Long dealerId);

    Integer deleteDealer(Long dealerId);
}
