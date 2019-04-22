package com.ningyang.os.action.input.command.web.serve;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author： kaider
 * @Date：2018/11/30 16:06
 * @描述：奖项设定
 */
@Data
public class PrizeSetCommand {

    private Long prizeSetId;

    private String prizeSetName;

    private Long managerId;

    private Long prodId;

    private Long memberType;

    private Long regionId;

    private int prizeQuantity;

    private BigDecimal money;

    private BigDecimal moneyEnd;

    private int ponit;

    private int pointEnd;

    private int prizeSetType;

    private int prizeModeType;

    private int cardMoney;

    private int cardCouponMoney;

    private Date[] prizeDate;

    private int setState;
}
