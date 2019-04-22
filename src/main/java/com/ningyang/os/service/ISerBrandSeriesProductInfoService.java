package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.ProductCommand;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.vo.api.ApiBrandSeriesProductVo;
import com.ningyang.os.action.output.vo.api.ApiProductVo;
import com.ningyang.os.action.output.vo.web.base.BrandSeriesProductVo;
import com.ningyang.os.action.output.vo.web.base.CodeTypeVo;
import com.ningyang.os.action.output.vo.web.base.ProductVo;
import com.ningyang.os.action.output.vo.web.base.SeriesProductVo;
import com.ningyang.os.action.output.vo.web.serve.BrandSeriesProductNameVo;
import com.ningyang.os.pojo.SerBrandSeriesProductInfo;

import java.util.List;


/**
 * <p>
 * 品牌系列产品信息 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface ISerBrandSeriesProductInfoService extends IService<SerBrandSeriesProductInfo> {

    List<ProductVo> findProductVoByCondition(QueryBrandSeriesProductCondition condition);

    boolean addOrUpdate(ProductCommand command);

    BrandSeriesProductVo findBrandSeriesProductVo(Long productId);

    List<CodeTypeVo> findCodeTypeVoByCondition(QueryBrandSeriesProductCondition condition);

    List<ApiBrandSeriesProductVo> findApiBrandSeriesProductVoCondition();

    List<SeriesProductVo> findSeriesProductVoByCondition(QueryBrandSeriesProductCondition condition);

    List<ApiProductVo> findApiProductVoList();

    BrandSeriesProductNameVo findBrandSeriesProductNameVo(Long productId);

    Page<ProductVo> findProductVoPageByCondition(QueryBrandSeriesProductCondition condition);

}
