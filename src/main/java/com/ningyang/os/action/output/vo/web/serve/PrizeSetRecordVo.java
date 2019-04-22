package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.Date;

/**
 * @Author： kaider
 * @Date：2018/12/25 16:06
 * @描述：布奖操作记录
 */
@Data
public class PrizeSetRecordVo {
    //记录id
    private Long recordId;
    //奖项id
    private Long prizeSetId;
    //奖项名称
    private String prizeSetName;
    //布奖种类（0：订单，1：产品）
    private int prizeSetType;
    //布奖开始日期
    private Date prizeStartDate;
    //
    private String prizeStartDateStr;
    //布奖结束日期
    private Date prizeEndDate;
    //
    private String prizeEndDateStr;
    //操作日期
    private Date createTime;
    //
    private String createTimeStr;
    //当前奖项设置的状态（0：有效，1：无效）
    private Integer prizeSetState;
    //创建人
    private String userName;
    //范围（也就是实际布上奖的箱数）
    private int prizeSetCount;
    //订单id
    private Long orderId;
    //订单号
    private String orderNo;
    //经销商
    private Long dealerId;

    private Long brandId;

    private Long seriesId;

    private Long productId;
    //当前库存总量
    private Long warehouseGoodCount;
}
