package com.ningyang.os.action.input.command.web.serve;

import com.ningyang.os.action.output.vo.web.serve.SaleOrderVo;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/30 16:06
 * @描述：布奖操作
 */
@Data
public class PrizeSetRecordCommand {
    //布奖操作id
    private Long recordId;
    //订单信息
    private List<SaleOrderVo> saleOrderVoList;
    //品牌id
    private Long brandId;
    //系列id
    private Long seriesId;
    //产品id
    private Long productId;
    //奖项id
    private Long prizeSetId;
    //奖项类型（0：订单，1：产品）
    private int prizeSetType;
    //当前奖项设置的状态（0：有效，1：无效）
    private int prizeSetState;
}
