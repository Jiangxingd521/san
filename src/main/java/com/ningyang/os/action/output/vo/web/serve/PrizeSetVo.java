package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/30 16:07
 * @描述：
 */
@Data
public class PrizeSetVo {

    private Long prizeSetId;

    private String prizeSetName;

    private Long managerId;

    private String managerName;

    private Long prodId;

    private String productName;

    private Long memberType;

    private Long regionId;

    private List<String> regionList;

    private int prizeQuantity;

    private String money;

    private String moneyEnd;

    private int ponit;

    private int pointEnd;

    private int prizeSetType;

    private int prizeModeType;

    private int cardMoney;

    private int cardCouponMoney;

    private Date[] prizeDate;

    private Date prizeStartDate;

    private String prizeStartDateStr;

    private Date prizeEndDate;

    private String prizeEndDateStr;

    private int setState;

    private String userName;

    private PrizeManagerVo prizeManager;
}
