package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/30 13:01
 * @描述：会员积分规则
 */
@Data
public class MemberPointRuleVo {

    private Long ruleId;

    private String ruleName;

    private String ruleValue;

    private String userName;

    private int ruleState;
}
