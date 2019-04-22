package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.api.ApiWarehouseSaleOrderCommand;
import com.ningyang.os.action.input.command.web.serve.OrderPurchaseCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.OrderPurchaseVo;
import com.ningyang.os.pojo.SerPurchaseOrderInfo;

import java.util.List;

/**
 * <p>
 * 商品退货订单 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface ISerPurchaseOrderInfoService extends IService<SerPurchaseOrderInfo> {

    Page<OrderPurchaseVo> findOrderPurchaseVoPageByCondition(QueryOrderCondition condition);

    boolean addOrUpdate(OrderPurchaseCommand command, Long operateUserId);

    List<OrderPurchaseVo> findOrderPurchaseVoListByCondition(QueryOrderCondition condition);

    List<OrderPurchaseVo> findOrderCompleteListByCondition(QueryOrderCondition condition);

    int getOrderBoxCount(QueryGoodsPutCondition condition);

    boolean apiWareHouseAdd(ApiWarehouseSaleOrderCommand command);
}
