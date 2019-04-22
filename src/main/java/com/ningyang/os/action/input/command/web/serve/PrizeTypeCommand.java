package com.ningyang.os.action.input.command.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/30 09:23
 * @描述：奖项类型
 */
@Data
public class PrizeTypeCommand {

    private Long typeId;

    private String typeCode;

    private String typeName;

    private String typeContent;

    private int typeState = 0;
}
