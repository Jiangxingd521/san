package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.api.ApiWarehouseOrderReturnPutInCommand;
import com.ningyang.os.action.input.command.api.ApiWarehousePutInCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.output.vo.api.ApiWarehouseGoodsVo;
import com.ningyang.os.action.output.vo.web.serve.GoodsPutInVo;
import com.ningyang.os.action.output.vo.web.serve.WarehouseInventoryVo;
import com.ningyang.os.pojo.LSerWarehouseGoodsInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品入库记录日志 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
public interface ILSerWarehouseGoodsInfoService extends IService<LSerWarehouseGoodsInfo> {

    Map<String, Object> add(ApiWarehousePutInCommand command);

    Page<GoodsPutInVo> findGoodsPutInVoPageByCondition(QueryGoodsPutCondition condition);

    int getWarehouseBoxCount(Long warehouseId);

    List<ApiWarehouseGoodsVo> findApiWarehouseGoodsVo(String productName);

    List<WarehouseInventoryVo> findWarehouseInventoryVoById(Long warehouseId);

    Map<String, Object> orderReturn(ApiWarehouseOrderReturnPutInCommand command);

    int getOrderInBoxCount(Long purchaseId);

    List<GoodsPutInVo> findGoodsPutInVoByCondition(QueryGoodsPutCondition condition);

}
