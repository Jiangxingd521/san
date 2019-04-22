package com.ningyang.os.action.input.command.web.serve;

import com.ningyang.os.action.output.vo.web.serve.OrderDetailVo;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/27 15:39
 * @描述：销售订单
 */
@Data
public class OrderSaleCommand {
    //订单id
    private Long orderId;
    //操作类型（0：创建，1：财务）
    private int operateType;
    //经销商id
    private Long dealerId;
    //产品id
    private Long productId;
    //产品数量
    private String productNumber;
    //订单状态
    private int orderState;
    //备注
    private String remark;
    //创建人
    private Long createUserId;
    //财务
    private Long financialId;
    //明细
    private List<OrderDetailVo> detailList;
}
