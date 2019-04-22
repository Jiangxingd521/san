package com.ningyang.os.action.input.command.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/12/06 15:33
 * @描述：仓库下单
 */
@Data
@ApiModel(value = "ApiWarehouseSaleOrderCommand", description = "仓库下单")
public class ApiWarehouseSaleOrderCommand {
    //经销商id
    @ApiModelProperty(value = "经销商id")
    private Long dealerId;
    //创建人
    private Long createUserId;
    //备注
    @ApiModelProperty(value = "备注")
    private String remark;
    //明细
    @ApiModelProperty(value = "明细")
    private List<ApiWarehouseOrderDetailCommand> detailList;
}
