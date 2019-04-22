package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.BrandCommand;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.vo.web.base.BrandVo;
import com.ningyang.os.pojo.SerBrandInfo;

import java.util.List;


/**
 * <p>
 * 企业品牌信息 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface ISerBrandInfoService extends IService<SerBrandInfo> {

    List<BrandVo> findBrandVoByCondition(QueryBrandSeriesProductCondition condition);

    boolean addOrUpdate(BrandCommand command);

    Page<BrandVo> findBrandVoPageByCondition(QueryBrandSeriesProductCondition condition);

}
