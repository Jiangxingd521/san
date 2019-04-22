package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.vo.web.base.SeriesVo;
import com.ningyang.os.pojo.SerBrandSeriesInfo;

import java.util.List;


/**
 * <p>
 * 品牌系列信息 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface SerBrandSeriesInfoMapper extends BaseMapper<SerBrandSeriesInfo> {

    List<SeriesVo> selectSeriesVoByCondition(QueryBrandSeriesProductCondition condition);

    List<SeriesVo> selectSeriesVoPageByCondition(QueryBrandSeriesProductCondition condition);

    Long selectSeriesVoPageCountByCondition(QueryBrandSeriesProductCondition condition);

}
