package com.ningyang.os.action.input.command.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/30 13:04
 * @描述：会员积分规则
 */
@Data
public class MemberPointRuleCommand {

    private Long ruleId;

    private String ruleName;

    private int ruleValue;

    private Long userId;

    private int ruleState = 0;
    //删除标识 0为未删除 1为已删除
    private int sdata1=0;

    public int getSdata1() {
        return sdata1;
    }

    public void setSdata1(int sdata1) {
        this.sdata1 = sdata1;
    }
}
