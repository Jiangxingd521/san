package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.vo.web.base.BrandVo;
import com.ningyang.os.pojo.SerBrandInfo;

import java.util.List;


/**
 * <p>
 * 企业品牌信息 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface SerBrandInfoMapper extends BaseMapper<SerBrandInfo> {

    List<BrandVo> selectBrandVoByCondition(QueryBrandSeriesProductCondition condition);

    List<BrandVo> selectBrandVoPageByCondition(QueryBrandSeriesProductCondition condition);

    Long selectBrandVoPageCountByCondition(QueryBrandSeriesProductCondition condition);

}
