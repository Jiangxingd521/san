package com.ningyang.os.action.input.condition.serve;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/27 14:50
 * @描述：订单
 */
@Data
public class QueryOrderCondition extends BaseCondition {
    //订单状态
    private Integer orderState;
    //订单id
    private Long orderId = -1L;
    //操作用户
    private Long userId;

    private Integer orderSaleFlag = 0;
    //退货订单id
    private Long purchaseId = -1L;

    //系列产品id
    private Long productId;

    //订单号（销售、退货）
    private String orderNo;
    //经销商
    private String dealerName;
}
