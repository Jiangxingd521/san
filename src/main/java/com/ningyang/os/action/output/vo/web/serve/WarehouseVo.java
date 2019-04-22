package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/15 14:05
 * @描述：仓库
 */
@Data
public class WarehouseVo {

    private Long warehouseId;

    private String warehouseName;

    private Long warehouseUserId;

    private String warehousePerson;

    private String warehousePersonMobile;
    //仓库总容量
    private String totalInventory;
    //当前容量
    private int usedTotalInventory;

    private String warehouseRemark;

    private int warehouseState;
}
