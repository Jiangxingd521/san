package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/29 18:28
 * @描述：奖项管理
 */
@Data
public class PrizeManagerVo {

    private Long managerId;

    private Long typeId;

    private String typeName;

    private String prizeName;

    private String prizeContent;

    private String prizeRemark;

    private String userName;

    private int prizeState = 0;
}
