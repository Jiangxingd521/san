package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/27 14:59
 * @描述：销售订单
 */
@Data
public class SaleOrderVo {
    //订单id
    private Long orderId;
    //销售订单
    private String orderNo;
    //经销商
    private Long dealerId;
    //经销商
    private String dealerName;
    //数量（箱）
    private int productNumber;
    //创建人
    private String createUserName;
    //财务
    private String financialUserName;
    //订单状态
    private int orderState;
    //备注
    private String remark;
    //已出货数
    private int outBoxCount;
    //订单时间
    private Date orderCreateTime;

    private String orderCreateTimeStr;
    //完成时间
    private Date orderCompleteTime;

    private String orderCompleteTimeStr;
    //订单详情
    private List<OrderDetailVo> detailList;
    //出货完成后的详情
    private List<GoodsPutOutVo> warehouseDetailList;
}
