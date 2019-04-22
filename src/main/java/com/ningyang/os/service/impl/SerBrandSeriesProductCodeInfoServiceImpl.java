package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.output.dto.serve.ProductCodeDto;
import com.ningyang.os.dao.SerBrandSeriesProductCodeInfoMapper;
import com.ningyang.os.pojo.SerBrandSeriesProductCodeInfo;
import com.ningyang.os.service.ISerBrandSeriesProductCodeInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品组成码信息 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-14
 */
@Service
public class SerBrandSeriesProductCodeInfoServiceImpl extends ServiceImpl<SerBrandSeriesProductCodeInfoMapper, SerBrandSeriesProductCodeInfo> implements ISerBrandSeriesProductCodeInfoService {

    @Override
    public List<Long> getProductCodeIds(Long productId) {
        List<Long> productCodeIdList = new ArrayList<>();
        List<SerBrandSeriesProductCodeInfo> productCodeInfoList = list(new QueryWrapper<SerBrandSeriesProductCodeInfo>()
                .eq("product_id", productId));

        for (SerBrandSeriesProductCodeInfo codeInfo : productCodeInfoList) {
            Long codeId = codeInfo.getCodeId();
            productCodeIdList.add(codeId);
        }
        return productCodeIdList;
    }

    @Override
    public List<ProductCodeDto> getProductCodeMake(Long productId) {
        return baseMapper.selectProductCodeMake(productId);
    }
}
