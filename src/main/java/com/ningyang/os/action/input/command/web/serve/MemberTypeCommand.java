package com.ningyang.os.action.input.command.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/30 13:13
 * @描述：会员类型
 */
@Data
public class MemberTypeCommand {

    private Long typeId;

    private Long ruleId;

    private String typeName;

    private int typeQuanty;

    private Long userId;

    private int typeState = 0;
}
