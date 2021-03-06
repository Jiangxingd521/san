package com.ningyang.os.action.output.vo.api;

import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/12/12 16:04
 * @描述：仓库商品信息
 */
@Data
public class ApiWarehouseGoodsVo {
    //品牌
    private String brandName;
    //系列
    private String seriesName;
    //产品
    private String productName;
    //产品id
    private Long productId;
    //仓库名称
    private String warehouseName;
    //箱数
    private int boxCount;
    //具体列表
    private List<ApiWarehouseGoodsInfoVo> goodsInfoVoList;
}
