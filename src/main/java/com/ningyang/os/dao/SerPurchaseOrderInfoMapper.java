package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.OrderPurchaseVo;
import com.ningyang.os.pojo.SerPurchaseOrderInfo;

import java.util.List;

/**
 * <p>
 * 商品退货订单 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface SerPurchaseOrderInfoMapper extends BaseMapper<SerPurchaseOrderInfo> {

    List<OrderPurchaseVo> selectOrderPurchaseVoPageByCondition(QueryOrderCondition condition);

    Long selectOrderPurchaseVoPageCountByCondition(QueryOrderCondition condition);

    List<OrderPurchaseVo> selectOrderPurchaseVoListByCondition(QueryOrderCondition condition);

    List<OrderPurchaseVo> selectOrderCompleteListByCondition();
}
