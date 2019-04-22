package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.OrderDetailVo;
import com.ningyang.os.pojo.SerOrderInfoDetails;

import java.util.List;

/**
 * <p>
 * 销售订单详情 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
public interface SerOrderInfoDetailsMapper extends BaseMapper<SerOrderInfoDetails> {

    List<OrderDetailVo> selectOrderDetailVoList(QueryOrderCondition condition);

    boolean deleteOrderByNull();

    int selectOrderDetailBoxCount(QueryOrderCondition condition);

}
