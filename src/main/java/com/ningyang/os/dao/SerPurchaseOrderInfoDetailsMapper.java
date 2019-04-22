package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.OrderDetailVo;
import com.ningyang.os.pojo.SerPurchaseOrderInfoDetails;

import java.util.List;

/**
 * <p>
 * 退货订单商品明细 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface SerPurchaseOrderInfoDetailsMapper extends BaseMapper<SerPurchaseOrderInfoDetails> {

    List<OrderDetailVo> selectOrderDetailVoList(QueryOrderCondition condition);

    int selectOrderDetailBoxCount(QueryOrderCondition condition);

    boolean deletePurchaseOrderByNull();
}
