package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.Date;

/**
 * @Author： kaider
 * @Date：2018/11/29 10:55
 * @描述：商品入库
 */
@Data
public class GoodsPutInVo {
    //仓库
    private String warehouseName;
    //品牌
    private String brandName;
    //产品
    private String seriesName;
    //系列
    private String productName;
    //箱数
    private String boxCount;
    //
    private Date createTime;
    //入库时间
    private String createTimeStr;
}
