package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.api.ApiWarehouseSaleOrderCommand;
import com.ningyang.os.action.input.command.web.serve.OrderSaleCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.SaleOrderVo;
import com.ningyang.os.pojo.SerOrderInfo;

import java.util.List;

/**
 * <p>
 * 销售订单表 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
public interface ISerOrderInfoService extends IService<SerOrderInfo> {

    Page<SaleOrderVo> findSaleOrderVoPageByCondition(QueryOrderCondition condition);

    boolean addOrUpdate(OrderSaleCommand command, Long operateUserId);

    List<SaleOrderVo> findSaleOrderVoListByCondition(QueryOrderCondition condition);

    int getOrderBoxCount(QueryGoodsPutCondition condition);

    boolean apiWareHouseAdd(ApiWarehouseSaleOrderCommand command);

    List<SaleOrderVo> findOrderCompleteListByCondition(QueryOrderCondition condition);

    SaleOrderVo getSaleOrderVoByOrderId(Long orderId);

}
