package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/30 13:17
 * @描述：会员类型
 */
@Data
public class MemberTypeVo {

    private Long typeId;

    private Long ruleId;

    private String ruleName;

    private String typeName;

    private int typeQuanty;

    private String userName;

    private int typeState;
}
