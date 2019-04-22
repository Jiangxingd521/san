package com.ningyang.os.action.output.vo.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/13 17:07
 * @描述：系列
 */
@Data
public class SeriesVo {
    //品牌
    private Long brandId;
    //品牌
    private String brandName;
    //系列
    private Long seriesId;
    //系列
    private String seriesName;
    //备注
    private String seriesRemark;
    //产品状态
    private int seriesState;
}
