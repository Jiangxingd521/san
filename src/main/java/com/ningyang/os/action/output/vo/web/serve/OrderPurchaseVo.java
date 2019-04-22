package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/12/10 14:52
 * @描述：退单
 */
@Data
public class OrderPurchaseVo {
    //退货订单id
    private Long purchaseId;
    //退货订单号
    private String orderNo;
    //经销商
    private Long dealerId;
    //经销商
    private String dealerName;
    //数量（箱）
    private int productNumber;
    //联系人
    private String personName;
    //联系人电话
    private String personMobile;
    //退货备注
    private String remark;
    //接收到的箱数
    private int receiveBoxNumber;
    //创建人
    private String createUserName;
    //财务
    private String financialUserName;
    //订单状态
    private int orderState;
    //订单时间
    private Date orderCreateTime;

    private String orderCreateTimeStr;
    //完成时间
    private Date orderCompleteTime;

    private String orderCompleteTimeStr;
    //订单详情
    private List<OrderDetailVo> detailList;
    //收货完成后的详情
    private List<GoodsPutInVo> warehouseDetailList;
}
