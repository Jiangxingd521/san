package com.ningyang.os.action.output.vo.api;

import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/12/06 14:39
 * @描述：品牌系列产品
 */
@Data
public class ApiSeriesVo {

    private Long seriesId;

    private String seriesName;

    private List<ApiProductVo> productListVo;
}
