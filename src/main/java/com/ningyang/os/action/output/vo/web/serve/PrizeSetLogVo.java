package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/12/03 18:06
 * @描述：布奖记录
 */
@Data
public class PrizeSetLogVo {

    private Long recodeId;

    private String prizeSetName;

    private String productName;

    private String prizeTypeName;

    private String userName;

    private int prizeState;
}
