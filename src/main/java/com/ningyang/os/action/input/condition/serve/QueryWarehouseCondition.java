package com.ningyang.os.action.input.condition.serve;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;
/**
 * @Author： kaider
 * @Date：2018/11/15 14:04
 * @描述：仓库
 */
@Data
public class QueryWarehouseCondition extends BaseCondition {
    //仓库状态
    private Integer warehouseState;
}
