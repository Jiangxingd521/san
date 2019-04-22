package com.ningyang.os.action.input.command.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/28 15:21
 * @描述：订单详情删除
 */
@Data
public class OrderDetailsDelCommand {

    private int type;

    private Long orderId;

    private Long purchaseId;

    private Long productId;
}
