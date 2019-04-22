package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.SeriesCommand;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.vo.web.base.SeriesVo;
import com.ningyang.os.dao.SerBrandSeriesInfoMapper;
import com.ningyang.os.pojo.SerBrandSeriesInfo;
import com.ningyang.os.service.ISerBrandSeriesInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 品牌系列信息 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@Service
public class SerBrandSeriesInfoServiceImpl extends ServiceImpl<SerBrandSeriesInfoMapper, SerBrandSeriesInfo> implements ISerBrandSeriesInfoService {

    @Override
    public Page<SeriesVo> findSeriesVoPageByCondition(QueryBrandSeriesProductCondition condition) {
        Page<SeriesVo> pageVo = new Page<>();
        List<SeriesVo> listVoTemp = baseMapper.selectSeriesVoPageByCondition(condition);
        long total = baseMapper.selectSeriesVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public List<SeriesVo> findSeriesVoByCondition(QueryBrandSeriesProductCondition condition) {
        return baseMapper.selectSeriesVoByCondition(condition);
    }

    @Override
    public boolean addOrUpdate(SeriesCommand command) {
        SerBrandSeriesInfo info = getOne(new QueryWrapper<SerBrandSeriesInfo>().eq("id", command.getSeriesId()));
        boolean flag;
        if (info != null) {
            info.setBrandId(command.getBrandId());
            info.setSeriesName(command.getSeriesName());
            info.setSeriesRemark(command.getSeriesRemark());
            info.setSeriesState(command.getSeriesState());
            info.setUpdateTime(new Date());
            flag = updateById(info);
        } else {
            info = new SerBrandSeriesInfo();
            info.setBrandId(command.getBrandId());
            info.setSeriesName(command.getSeriesName());
            info.setSeriesRemark(command.getSeriesRemark());
            info.setSeriesState(0);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag = save(info);
        }
        return flag;
    }
}
