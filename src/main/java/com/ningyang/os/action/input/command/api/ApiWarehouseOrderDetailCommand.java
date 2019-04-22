package com.ningyang.os.action.input.command.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/12/06 15:38
 * @描述：
 */
@Data
public class ApiWarehouseOrderDetailCommand {
    @ApiModelProperty(value = "产品系列id")
    private Long productId;
    @ApiModelProperty(value = "箱数")
    private int boxNumber;
}
