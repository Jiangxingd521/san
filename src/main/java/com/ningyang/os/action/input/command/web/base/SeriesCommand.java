package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/13 17:20
 * @描述：系列
 */
@Data
public class SeriesCommand {
    //
    private Long seriesId;
    //
    private String seriesName;
    //
    private Long brandId;
    //
    private String brandName;
    //
    private String seriesRemark;
    //
    private int seriesState;
}
