package com.ningyang.os.controller.serve;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.serve.OrderDetailsCommand;
import com.ningyang.os.action.input.command.web.serve.OrderDetailsDelCommand;
import com.ningyang.os.action.input.command.web.serve.OrderPurchaseCommand;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.OrderDetailVo;
import com.ningyang.os.action.output.vo.web.serve.OrderPurchaseVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.pojo.SerPurchaseOrderInfoDetails;
import com.ningyang.os.service.ISerPurchaseOrderInfoDetailsService;
import com.ningyang.os.service.ISerPurchaseOrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.*;

/**
 * @Author： kaider
 * @Date：2018/12/04 14:31
 * @描述：退货
 */
@RestController
@RequestMapping("serve/order/return")
public class OrderReturnController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderReturnController.class);

    @Autowired
    private ISerPurchaseOrderInfoService infoService;
    @Autowired
    private ISerPurchaseOrderInfoDetailsService detailsService;


    @GetMapping("getPurchasePage")
    public Map<String, Object> getPurchasePage(
            QueryOrderCondition condition
    ) {
        try {
            Page<OrderPurchaseVo> pageVo = infoService.findOrderPurchaseVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }


    @PostMapping("addPurchase")
    public Map<String, Object> addOrUpdatePurchase(
            @RequestHeader("Authorization") String userToken,
            @RequestBody OrderPurchaseCommand command
    ) {
        try {
            Long operateUserId = getBaseUserInfo(userToken).getId();
            boolean flag = infoService.addOrUpdate(command, operateUserId);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }


    @DeleteMapping("deletePurchase/{orderId}")
    public Map<String, Object> deletePurchase(
            @PathVariable("orderId") Long orderId
    ) {
        try {
            boolean flag = infoService.removeById(orderId);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(DELETE_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DELETE_ERROR.getInfo()).toMap();
        }
    }


    @GetMapping("getOrderDetailList")
    public Map<String, Object> getOrderDetailList(
            @RequestHeader("Authorization") String userToken,
            QueryOrderCondition condition
    ) {
        try {
            Long operateUserId = getBaseUserInfo(userToken).getId();
            condition.setUserId(operateUserId);
            List<OrderDetailVo> listVo = detailsService.findOrderDetailVoList(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DELETE_ERROR.getInfo()).toMap();
        }
    }


    @PostMapping("addDetails")
    public Map<String, Object> addDetails(
            @RequestHeader("Authorization") String userToken,
            @RequestBody OrderDetailsCommand command
    ) {
        try {
            Long operateUserId = getBaseUserInfo(userToken).getId();
            boolean flag = detailsService.add(command, operateUserId);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }


    @DeleteMapping("deleteDetails")
    public Map<String, Object> deleteDetails(
            @RequestHeader("Authorization") String userToken,
            @RequestBody OrderDetailsDelCommand command
    ) {
        try {
            Long operateUserId = getBaseUserInfo(userToken).getId();
            boolean flag;
            if (command.getType() == 1) {
                flag = detailsService.remove(new QueryWrapper<SerPurchaseOrderInfoDetails>()
                        .eq("purchase_id", -1)
                        .eq("user_id", operateUserId));
            } else {
                flag = detailsService.remove(new QueryWrapper<SerPurchaseOrderInfoDetails>()
                        .eq("purchase_id",command.getPurchaseId())
                        .eq("product_id",command.getProductId()));
            }
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }

}
