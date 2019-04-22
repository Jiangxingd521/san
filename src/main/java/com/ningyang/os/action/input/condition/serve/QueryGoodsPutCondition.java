package com.ningyang.os.action.input.condition.serve;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/29 10:53
 * @描述：商品出入库
 */
@Data
public class QueryGoodsPutCondition extends BaseCondition {
    //订单号
    private String orderNo;
    //订单id
    private Long orderId;
    //产品系列id
    private Long productId;
    //退货订单id
    private Long purchaseId;
}
