package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.SeriesCommand;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.vo.web.base.SeriesVo;
import com.ningyang.os.pojo.SerBrandSeriesInfo;

import java.util.List;


/**
 * <p>
 * 品牌系列信息 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface ISerBrandSeriesInfoService extends IService<SerBrandSeriesInfo> {

    List<SeriesVo> findSeriesVoByCondition(QueryBrandSeriesProductCondition condition);

    boolean addOrUpdate(SeriesCommand command);

    Page<SeriesVo> findSeriesVoPageByCondition(QueryBrandSeriesProductCondition condition);
}
