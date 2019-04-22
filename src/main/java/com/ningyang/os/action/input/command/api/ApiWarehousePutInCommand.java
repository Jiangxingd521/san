package com.ningyang.os.action.input.command.api;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/27 10:39
 * @描述：商品入库
 */
@Data
public class ApiWarehousePutInCommand {
    //仓库
    private Long warehouseId;
    //操作人
    private Long userId;
    //备注
    private String remark;
    //箱码
    private String[] boxCode;
}
