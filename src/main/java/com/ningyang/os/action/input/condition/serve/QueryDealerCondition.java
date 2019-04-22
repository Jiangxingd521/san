package com.ningyang.os.action.input.condition.serve;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/14 16:53
 * @描述：经销商
 */
@Data
public class QueryDealerCondition extends BaseCondition {
    //经销商
    private String dealerName;
    //联系人
    private String personName;
    //联系人电话
    private String personMobile;
}
