package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberInfoVo;
import com.ningyang.os.dao.MemberInfoMapper;
import com.ningyang.os.pojo.MemberInfo;
import com.ningyang.os.service.IMemberInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.timeToStr;

/**
 * <p>
 * 会员数据 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@Service
public class MemberInfoServiceImpl extends ServiceImpl<MemberInfoMapper, MemberInfo> implements IMemberInfoService {

    @Override
    public Page<MemberInfoVo> findMemberInfoVoPageByCondition(QueryMemberCondition condition) {
        Page<MemberInfoVo> pageVo = new Page<>();
        List<MemberInfoVo> listVoTemp = baseMapper.selectMemberInfoVoPageByCondition(condition);
        for (MemberInfoVo vo : listVoTemp) {
            vo.setCreateTimeStr(timeToStr(vo.getCreateTime()));
        }
        long total = baseMapper.selectMemberInfoVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }
}
