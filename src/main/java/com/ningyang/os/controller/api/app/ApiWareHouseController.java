package com.ningyang.os.controller.api.app;

import com.ningyang.os.action.input.command.api.ApiWarehouseOrderReturnPutInCommand;
import com.ningyang.os.action.input.command.api.ApiWarehousePutInCommand;
import com.ningyang.os.action.input.command.api.ApiWarehousePutOutCommand;
import com.ningyang.os.action.input.command.api.ApiWarehouseSaleOrderCommand;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.input.condition.serve.QueryWarehouseCondition;
import com.ningyang.os.action.output.dto.serve.PutOutDto;
import com.ningyang.os.action.output.vo.api.ApiBrandSeriesProductVo;
import com.ningyang.os.action.output.vo.api.ApiProductVo;
import com.ningyang.os.action.output.vo.api.ApiWarehouseGoodsVo;
import com.ningyang.os.action.output.vo.web.serve.DealerVo;
import com.ningyang.os.action.output.vo.web.serve.OrderPurchaseVo;
import com.ningyang.os.action.output.vo.web.serve.SaleOrderVo;
import com.ningyang.os.action.output.vo.web.serve.WarehouseVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.pojo.LSerWarehouseGoodsInfo;
import com.ningyang.os.pojo.SysUserInfo;
import com.ningyang.os.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.*;

/**
 * @Author： kaider
 * @Date：2018/11/27 10:21
 * @描述：
 */
@Api(tags = {"仓管操作模块"})
@RestController
@RequestMapping("api/app/warehouse")
public class ApiWareHouseController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiWareHouseController.class);

    @Autowired
    private ILSerWarehouseGoodsInfoService putInService;
    @Autowired
    private ILSerWarehouseGoodsOutInfoService putOutService;
    @Autowired
    private ISerOrderInfoService orderInfoService;
    @Autowired
    private ISerWarehouseInfoService warehouseInfoService;
    @Autowired
    private ISerDealerInfoService dealerInfoService;
    @Autowired
    private ISerBrandSeriesProductInfoService productInfoService;
    @Autowired
    private ISerPurchaseOrderInfoService purchaseOrderInfoService;


    @ApiOperation(value = "仓库列表")
    @GetMapping("getWarehouseInfoList")
    public Map<String, Object> getWarehouseInfoList(
            @RequestHeader("Authorization") String userToken,
            HttpServletResponse response
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                QueryWarehouseCondition condition = new QueryWarehouseCondition();
                condition.setWarehouseState(1);
                List<WarehouseVo> listVo = warehouseInfoService.findWarehouseVoListByCondition(condition);
                Map<String, Object> map = new HashMap<>();
                map.put("listVo", listVo);
                return WebResult.success().put("data", map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "入库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "warehouseId", value = "仓库Id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "boxCode", value = "箱码", required = true, paramType = "query", allowMultiple = true),
            @ApiImplicitParam(name = "remark", value = "入库备注", paramType = "query")
    })
    @PostMapping("putIn")
    public Map<String, Object> putIn(
            @RequestHeader("Authorization") String userToken,
            ApiWarehousePutInCommand command,
            HttpServletResponse response
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                command.setUserId(loginUser.getId());
                Map<String, Object> saveMap = putInService.add(command);
                boolean flag = (boolean) saveMap.get("saveFlag");
                List<LSerWarehouseGoodsInfo> unSaveList = (List<LSerWarehouseGoodsInfo>) saveMap.get("unSaveFlag");
                Map<String, Object> map = new HashMap<>();
                map.put("unSaveList", unSaveList);
                if (flag) {
                    return WebResult.success().put("data", map).toMap();
                }
                return WebResult.failure(PUTIN_WAREHOUSE_ERROR.getInfo()).put("data", map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "销售订单列表")
    @GetMapping("getOrderSaleList")
    public Map<String, Object> getOrderSaleList(
            @RequestHeader("Authorization") String userToken,
            HttpServletResponse response
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                QueryOrderCondition condition = new QueryOrderCondition();
                List<SaleOrderVo> listVo = orderInfoService.findSaleOrderVoListByCondition(condition);
                Map<String, Object> map = new HashMap<>();
                map.put("listVo", listVo);
                return WebResult.success().put("data", map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "出库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单号Id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "boxCode", value = "箱码", required = true, paramType = "query", allowMultiple = true),
            @ApiImplicitParam(name = "warehouseId", value = "仓库", paramType = "query")
    })
    @PostMapping("putOut")
    public Map<String, Object> putOut(
            @RequestHeader("Authorization") String userToken,
            ApiWarehousePutOutCommand command,
            HttpServletResponse response
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                command.setUserId(loginUser.getId());
                Map<String, Object> flagMap = putOutService.add(command);
                PutOutDto putOutDto = (PutOutDto) flagMap.get("putOutFlag");
                Map<String, Object> map = new HashMap<>();
                if (putOutDto.getFlag()) {
                    return WebResult.success().toMap();
                } else {
                    map.put("putOutDto", putOutDto);
                    return WebResult.failure(PUTOUT_WAREHOUSE_ERROR.getInfo()).put("data", map).toMap();
                }
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "经销商列表")
    @GetMapping("getDealerList")
    public Map<String, Object> getDealerList(
            @RequestHeader("Authorization") String userToken,
            HttpServletResponse response
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                List<DealerVo> listVo = dealerInfoService.findDealerVoListByCondition();
                Map<String, Object> map = new HashMap<>();
                map.put("listVo", listVo);
                return WebResult.success().put("data", map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "品牌系列商品列表")
    @GetMapping("getBrandSeriesProductList")
    public Map<String, Object> getBrandSeriesProductList(
            @RequestHeader("Authorization") String userToken,
            HttpServletResponse response
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                List<ApiBrandSeriesProductVo> listVo = productInfoService.findApiBrandSeriesProductVoCondition();
                Map<String, Object> map = new HashMap<>();
                map.put("listVo", listVo);
                return WebResult.success().put("data", map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "创建销售订单")
    @PostMapping("addOrder")
    public Map<String, Object> addOrder(
            @RequestHeader("Authorization") String userToken,
            @RequestBody ApiWarehouseSaleOrderCommand command,
            HttpServletResponse response
    ) {
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                command.setCreateUserId(loginUser.getId());
                //仓管提供的订单数据
                boolean flag = orderInfoService.apiWareHouseAdd(command);
                if(flag){
                    return WebResult.success().toMap();
                }
                return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "所有系列产品")
    @GetMapping("getProductList")
    public Map<String,Object> getProductList(
            @RequestHeader("Authorization") String userToken,
            HttpServletResponse response
    ){
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                Map<String,Object> map = new HashMap<>();
                List<ApiProductVo> productVoList = productInfoService.findApiProductVoList();
                map.put("listVo",productVoList);
                return WebResult.success().put("data",map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "库存查询")
    @ApiImplicitParam(name = "productName", value = "系列产品名称", required = true, paramType = "query")
    @GetMapping("getWarehouseGoodsInfo")
    public Map<String,Object> getWarehouseGoodsInfo(
            @RequestHeader("Authorization") String userToken,
            String productName,
            HttpServletResponse response
    ){
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                List<ApiWarehouseGoodsVo> listVo = putInService.findApiWarehouseGoodsVo(productName);
                Map<String,Object> map = new HashMap<>();
                map.put("listVo",listVo);
                return WebResult.success().put("data",map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "已完成销售订单")
    @GetMapping("getOrderCompleteList")
    public Map<String,Object> getOrderCompleteList(
            @RequestHeader("Authorization") String userToken,
            HttpServletResponse response
    ){
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                QueryOrderCondition condition = new QueryOrderCondition();
                condition.setOrderState(4);
                List<SaleOrderVo> listVo = orderInfoService.findOrderCompleteListByCondition(condition);
                Map<String,Object> map = new HashMap<>();
                map.put("listVo",listVo);
                return WebResult.success().put("data",map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "退货订单列表")
    @GetMapping("getOrderReturnList")
    public Map<String,Object> getOrderReturnList(
            @RequestHeader("Authorization") String userToken,
            HttpServletResponse response
    ){
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                QueryOrderCondition condition = new QueryOrderCondition();
                List<OrderPurchaseVo> listVo = purchaseOrderInfoService.findOrderPurchaseVoListByCondition(condition);
                Map<String, Object> map = new HashMap<>();
                map.put("listVo", listVo);
                return WebResult.success().put("data", map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "退货入库")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "warehouseId", value = "仓库Id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "boxCode", value = "箱码", required = true, paramType = "query", allowMultiple = true),
            @ApiImplicitParam(name = "remark", value = "入库备注", paramType = "query")
    })
    @PostMapping("orderReturnPutIn")
    public Map<String,Object> orderReturnPutIn(
            @RequestHeader("Authorization") String userToken,
            ApiWarehouseOrderReturnPutInCommand command,
            HttpServletResponse response
    ){
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                command.setUserId(loginUser.getId());
                Map<String, Object> saveMap = putInService.orderReturn(command);
                boolean flag = (boolean) saveMap.get("saveFlag");
                List<LSerWarehouseGoodsInfo> unSaveList = (List<LSerWarehouseGoodsInfo>) saveMap.get("unSaveFlag");
                Map<String, Object> map = new HashMap<>();
                map.put("unSaveList", unSaveList);
                if (flag) {
                    return WebResult.success().put("data", map).toMap();
                }
                return WebResult.failure(PUTIN_WAREHOUSE_ERROR.getInfo()).put("data", map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "已完成退货订单")
    @GetMapping("getOrderReturnCompleteList")
    public Map<String,Object> getOrderReturnCompleteList(
            @RequestHeader("Authorization") String userToken,
            HttpServletResponse response
    ){
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                QueryOrderCondition condition = new QueryOrderCondition();
                condition.setOrderState(4);
                List<OrderPurchaseVo> listVo = purchaseOrderInfoService.findOrderCompleteListByCondition(condition);
                Map<String,Object> map = new HashMap<>();
                map.put("listVo",listVo);
                return WebResult.success().put("data",map).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

    @ApiOperation(value = "创建退货订单")
    @PostMapping("addOrderReturn")
    public Map<String,Object> addOrderReturn(
            @RequestHeader("Authorization") String userToken,
            @RequestBody ApiWarehouseSaleOrderCommand command,
            HttpServletResponse response
    ){
        try {
            SysUserInfo loginUser = getBaseUserInfo(userToken);
            if (loginUser != null) {
                command.setCreateUserId(loginUser.getId());
                //仓管提供的订单数据
                boolean flag = purchaseOrderInfoService.apiWareHouseAdd(command);
                if(flag){
                    return WebResult.success().toMap();
                }
                return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
            }
            response.setStatus(300);
            return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(API_REQUEST_ERROR.getInfo()).toMap();
        }
    }

}
