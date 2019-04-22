package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.output.dto.serve.ProductCodeDto;
import com.ningyang.os.pojo.SerBrandSeriesProductCodeInfo;

import java.util.List;

/**
 * <p>
 * 产品组成码信息 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-14
 */
public interface SerBrandSeriesProductCodeInfoMapper extends BaseMapper<SerBrandSeriesProductCodeInfo> {

    List<ProductCodeDto> selectProductCodeMake(Long productId);

}
