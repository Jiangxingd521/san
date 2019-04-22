package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.BarCommand;
import com.ningyang.os.action.input.condition.base.QueryMenuCondition;
import com.ningyang.os.action.output.vo.web.base.BarVo;
import com.ningyang.os.dao.SysNavigationBarInfoMapper;
import com.ningyang.os.pojo.SysNavigationBarInfo;
import com.ningyang.os.service.ISysNavigationBarInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 导航栏信息表 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@Service
public class SysNavigationBarInfoServiceImpl extends ServiceImpl<SysNavigationBarInfoMapper, SysNavigationBarInfo> implements ISysNavigationBarInfoService {

    @Override
    public List<BarVo> findBarVoListByCondition(QueryMenuCondition condition) {
        return baseMapper.selectBarVoListByCondition(condition);
    }

    @Override
    public List<BarVo> findUserBarVoListByCondition(QueryMenuCondition condition) {
        return baseMapper.selectUserBarVoListByCondition(condition);
    }

    @Override
    public boolean add(BarCommand command) {
        SysNavigationBarInfo barInfo = new SysNavigationBarInfo();
        barInfo.setBarName(command.getBarName());
        barInfo.setBarSort(command.getBarSort());
        barInfo.setBarPath(command.getBarPath());
        barInfo.setBarIcon(command.getBarIcon());
        barInfo.setBarState(0);
        barInfo.setRemark(command.getRemark());
        barInfo.setCreateTime(new Date());
        barInfo.setUpdateTime(new Date());
        return save(barInfo);
    }

    @Override
    public boolean edit(BarCommand command) {
        SysNavigationBarInfo barInfo = getById(command.getBarId());
        barInfo.setBarName(command.getBarName());
        barInfo.setBarSort(command.getBarSort());
        barInfo.setBarPath(command.getBarPath());
        barInfo.setBarIcon(command.getBarIcon());
        barInfo.setBarState(command.getBarState());
        barInfo.setRemark(command.getRemark());
        barInfo.setUpdateTime(new Date());
        return updateById(barInfo);
    }

    @Override
    public String findUserWelTagByUserId(Long userId) {
        return baseMapper.selectUserWelTagByUserId(userId);
    }

}
