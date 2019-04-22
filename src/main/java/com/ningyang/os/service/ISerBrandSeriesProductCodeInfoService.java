package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.output.dto.serve.ProductCodeDto;
import com.ningyang.os.pojo.SerBrandSeriesProductCodeInfo;

import java.util.List;

/**
 * <p>
 * 产品组成码信息 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-14
 */
public interface ISerBrandSeriesProductCodeInfoService extends IService<SerBrandSeriesProductCodeInfo> {

    List<Long> getProductCodeIds(Long productId);

    List<ProductCodeDto> getProductCodeMake(Long productId);

}
