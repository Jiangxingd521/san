package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/29 18:28
 * @描述：奖项类型
 */
@Data
public class PrizeTypeVo {

    private Long typeId;

    private String typeCode;

    private String typeName;

    private String typeContent;

    private int typeState;

    private String userName;
}
