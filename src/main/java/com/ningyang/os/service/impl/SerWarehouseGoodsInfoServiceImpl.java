package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.dao.SerWarehouseGoodsInfoMapper;
import com.ningyang.os.pojo.SerWarehouseGoodsInfo;
import com.ningyang.os.service.ISerWarehouseGoodsInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品入库 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@Service
public class SerWarehouseGoodsInfoServiceImpl extends ServiceImpl<SerWarehouseGoodsInfoMapper, SerWarehouseGoodsInfo> implements ISerWarehouseGoodsInfoService {

    @Override
    public int getWarehouseGoodsCount(Long productId) {
        return baseMapper.getWarehouseGoodsCount(productId);
    }
}
