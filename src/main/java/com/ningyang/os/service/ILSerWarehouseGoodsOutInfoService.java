package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.api.ApiWarehousePutOutCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.output.vo.web.serve.GoodsPutOutVo;
import com.ningyang.os.pojo.LSerWarehouseGoodsOutInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品出库记录日志 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
public interface ILSerWarehouseGoodsOutInfoService extends IService<LSerWarehouseGoodsOutInfo> {

    Map<String, Object> add(ApiWarehousePutOutCommand command);

    Page<GoodsPutOutVo> findGoodsPutOutVoPageByCondition(QueryGoodsPutCondition condition);

    List<GoodsPutOutVo> findGoodsPutOutVoByCondition(QueryGoodsPutCondition condition);

    int getWarehouseBoxCount(Long warehouseId);

    int getOrderOutBoxCount(Long orderId);

    List<GoodsPutOutVo> findWarehouseGoodsPutOutVoByCondition(QueryGoodsPutCondition condition);

}
