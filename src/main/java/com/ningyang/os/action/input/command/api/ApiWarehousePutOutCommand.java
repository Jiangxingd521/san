package com.ningyang.os.action.input.command.api;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/28 10:03
 * @描述：商品出库
 */
@Data
public class ApiWarehousePutOutCommand {
    //销售订单id
    private Long orderId;
    //仓库
    private Long warehouseId;
    //操作人
    private Long userId;
    //箱码
    private String[] boxCode;
}
