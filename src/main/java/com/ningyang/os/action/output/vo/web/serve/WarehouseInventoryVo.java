package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/15 14:05
 * @描述：仓库明细
 */
@Data
public class WarehouseInventoryVo {

    private Long productId;

    private String brandName;

    private String seriesName;

    private String productName;
    //当前容量
    private int usedTotalInventory;
}
