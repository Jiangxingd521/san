package com.ningyang.os.action.input.condition.serve;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/30 13:00
 * @描述：会员
 */
@Data
public class QueryMemberCondition extends BaseCondition {
    //规则状态
    private Integer ruleState;
    //类型状态
    private Integer typeState;
}
