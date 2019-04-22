package com.ningyang.os.action.input.condition.base;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/13 14:33
 * @描述：品牌产品系列
 */
@Data
public class QueryBrandSeriesProductCondition extends BaseCondition {
    //品牌id
    private Long brandId;
    //品牌状态
    private Integer brandState;
    //系列id
    private Long seriesId;
    //系列状态
    private Integer seriesState;
    //产品id
    private Long productId;
    //产品状态
    private Integer productState;
}
