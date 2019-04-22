package com.ningyang.os.action.output.vo.api;

import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/12/06 14:37
 * @描述：品牌系列产品
 */
@Data
public class ApiBrandSeriesProductVo {

    private Long brandId;

    private String brandName;

    private List<ApiSeriesVo> seriesListVo;
}
