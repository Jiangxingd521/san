package com.ningyang.os.action.input.condition.serve;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/29 18:25
 * @描述：奖项
 */
@Data
public class QueryPrizeCondition extends BaseCondition {

    private Integer typeState;

    private Integer prizeSetType;

    private Integer prizeSetState;
}


