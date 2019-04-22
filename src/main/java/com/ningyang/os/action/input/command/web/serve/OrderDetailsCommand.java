package com.ningyang.os.action.input.command.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/28 14:36
 * @描述：订单详情
 */
@Data
public class OrderDetailsCommand {
    //操作人
    private Long userId;
    //产品
    private Long productId;
    //产品数量
    private int boxNumber;
    //销售订单id
    private Long orderId;
    //退货订单id
    private Long purchaseId;
}
