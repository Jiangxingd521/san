package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.OrderDetailsCommand;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.OrderDetailVo;
import com.ningyang.os.pojo.SerOrderInfoDetails;

import java.util.List;

/**
 * <p>
 * 销售订单详情 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
public interface ISerOrderInfoDetailsService extends IService<SerOrderInfoDetails> {

    boolean add(OrderDetailsCommand command, Long operateUserId);

    List<OrderDetailVo> findOrderDetailVoList(QueryOrderCondition condition);

    int boxCount(QueryOrderCondition condition);

    boolean delete();

}
