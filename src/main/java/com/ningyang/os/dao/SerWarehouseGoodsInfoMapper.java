package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.pojo.SerWarehouseGoodsInfo;

/**
 * <p>
 * 商品入库 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
public interface SerWarehouseGoodsInfoMapper extends BaseMapper<SerWarehouseGoodsInfo> {

    int getWarehouseGoodsCount(Long productId);

}
