package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.SaleOrderVo;
import com.ningyang.os.pojo.SerOrderInfo;

import java.util.List;

/**
 * <p>
 * 销售订单表 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
public interface SerOrderInfoMapper extends BaseMapper<SerOrderInfo> {

    List<SaleOrderVo> selectSaleOrderVoPageByCondition(QueryOrderCondition condition);

    Long selectSaleOrderVoPageCountByCondition(QueryOrderCondition condition);

    List<SaleOrderVo> selectSaleOrderVoListByCondition(QueryOrderCondition condition);

    List<SaleOrderVo> selectOrderCompleteListByCondition(QueryOrderCondition condition);

}
