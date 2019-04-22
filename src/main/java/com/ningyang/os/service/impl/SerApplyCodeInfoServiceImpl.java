package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.ApplyCodeCommand;
import com.ningyang.os.action.input.command.web.serve.CenterCodeCommand;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.output.vo.web.serve.ApplyCodeVo;
import com.ningyang.os.dao.SerApplyCodeInfoMapper;
import com.ningyang.os.pojo.SerApplyCodeInfo;
import com.ningyang.os.service.ISerApplyCodeInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.getSimpleYearMonthShort;
import static com.ningyang.os.action.utils.DateUtil.timeToStr;


/**
 * <p>
 * 溯源码申请 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-15
 */
@Service
public class SerApplyCodeInfoServiceImpl extends ServiceImpl<SerApplyCodeInfoMapper, SerApplyCodeInfo> implements ISerApplyCodeInfoService {


    @Override
    public Page<ApplyCodeVo> findApplyCodeVoPageByCondition(QueryCodeCondition condition) {
        Page<ApplyCodeVo> pageVo = new Page<>();
        List<ApplyCodeVo> listVoTemp = baseMapper.selectApplyCodeVoPageByCondition(condition);
        for (ApplyCodeVo vo : listVoTemp) {
            vo.setCreateTimeStr(timeToStr(vo.getCreateTime()));
        }
        long total = baseMapper.selectApplyCodeVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    /**
     * 企业向中心发送请求
     *
     * @param command
     * @return
     */
    @Override
    public boolean add(ApplyCodeCommand command) {
        SerApplyCodeInfo info = new SerApplyCodeInfo();
        info.setCodeOrder(command.getCodeOrder());
        info.setApplyUserId(command.getApplyUserId());
        info.setCodeTypeId(command.getCodeType());
        info.setCodePositionId(command.getCodePosition());
        info.setCodePositionTypeId(command.getCodePositionType());
        info.setApplyCount(command.getApplyCount());
        info.setApplyState(0);
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        return save(info);
    }

    @Override
    public boolean updateApplyState(CenterCodeCommand command) {
        SerApplyCodeInfo info = getOne(new QueryWrapper<SerApplyCodeInfo>().eq("code_order", command.getCodeOrder()));
        info.setApplyState(command.getApplyState());
        info.setCodeTableName(getSimpleYearMonthShort());
        info.setUpdateTime(new Date());
        return updateById(info);
    }
}
