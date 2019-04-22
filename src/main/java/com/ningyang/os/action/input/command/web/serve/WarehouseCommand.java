package com.ningyang.os.action.input.command.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/15 14:52
 * @描述：仓库
 */
@Data
public class WarehouseCommand {

    private Long warehouseId;

    private String warehouseName;

    private Long warehouseUserId;

    private String warehousePerson;

    private String warehousePersonMobile;

    private String totalInventory;

    private String warehouseRemark;

    private int warehouseState;
}
