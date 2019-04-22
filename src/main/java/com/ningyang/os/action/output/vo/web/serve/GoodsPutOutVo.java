package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.Date;

/**
 * @Author： kaider
 * @Date：2018/11/29 10:55
 * @描述：商品出库
 */
@Data
public class GoodsPutOutVo {
    //经销商
    private String dealerName;
    //订单号
    private String orderNo;
    //订单id
    private Long orderId;
    //品牌
    private String brandName;
    //产品
    private String seriesName;
    //系列
    private String productName;
    //订单箱数
    private int orderBoxCount;
    //已出箱数
    private int outBoxCount;
    //操作人
    private String userName;
    //
    private Date goodsOutTime;
    //出库时间
    private String goodsOutTimeStr;
    //完成状态（0：未完成，1：已完成）
    private int finishFlag;
    //产品系列id
    private Long productId;
    //商品id
    private Long goodsId;
    //唯一码
    private String productCode;
}
