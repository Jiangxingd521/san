package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/28 15:06
 * @描述：订单详情
 */
@Data
public class OrderDetailVo {
    //订单id
    private Long orderId;
    //品牌名称
    private String brandName;
    //产品名称
    private String seriesName;
    //产品系列
    private Long productId;
    private String productName;
    //销售单价
    private String salesPrice;
    //箱数
    private int boxNumber;
    //订单明细状态
    private int detailState;
    //订单销售状态（0：未完成，1：已完成）
    private int saleState;
}
