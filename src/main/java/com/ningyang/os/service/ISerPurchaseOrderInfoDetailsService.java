package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.OrderDetailsCommand;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.OrderDetailVo;
import com.ningyang.os.pojo.SerPurchaseOrderInfoDetails;

import java.util.List;

/**
 * <p>
 * 退货订单商品明细 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface ISerPurchaseOrderInfoDetailsService extends IService<SerPurchaseOrderInfoDetails> {

    List<OrderDetailVo> findOrderDetailVoList(QueryOrderCondition condition);

    boolean add(OrderDetailsCommand command, Long operateUserId);

    int boxCount(QueryOrderCondition condition);

    boolean delete();
}
