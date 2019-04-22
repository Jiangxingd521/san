package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.Date;

/**
 * @Author： kaider
 * @Date：2018/12/04 10:29
 * @描述：兑奖记录
 */
@Data
public class PrizeTicketLogVo {
    //产品名称
    private String productName;
    //奖项名称
    private String prizeSetName;
    //奖项类型
    private String prizeTypeName;
    //经销商
    private String dealerName;
    //订单号
    private String orderNo;
    //兑奖时间
    private Date ticketTime;

    private String ticketTimeStr;
}
