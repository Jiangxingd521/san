package com.ningyang.os.action.input.command.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/30 10:10
 * @描述：奖项管理
 */
@Data
public class PrizeManagerCommand {

    private Long managerId;

    private Long typeId;

    private String prizeName;

    private String prizeContent;

    private String prizeRemark;

    private Long userId;

    private int prizeState = 0;
}
