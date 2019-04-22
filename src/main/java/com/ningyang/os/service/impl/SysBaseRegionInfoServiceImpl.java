package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.condition.base.QueryRegionCondition;
import com.ningyang.os.action.output.vo.web.base.RegionVo;
import com.ningyang.os.action.output.vo.web.base.SysRegionThreeVo;
import com.ningyang.os.action.output.vo.web.base.SysRegionVo;
import com.ningyang.os.dao.SysBaseRegionInfoMapper;
import com.ningyang.os.pojo.SysBaseRegionInfo;
import com.ningyang.os.service.ISysBaseRegionInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ningyang.os.action.utils.TreeUtil.getTreeList;
import static com.ningyang.os.action.utils.TreeUtil.getTreeNodeList;

/**
 * <p>
 * 系统区域信息表 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@Service
public class SysBaseRegionInfoServiceImpl extends ServiceImpl<SysBaseRegionInfoMapper, SysBaseRegionInfo> implements ISysBaseRegionInfoService {

    @Override
    public List<SysRegionVo> findSysRegionVoList() {
        List<SysRegionVo> listTemp = baseMapper.selectSysRegionVoList();
        List<SysRegionVo> regions = getTreeList("0", listTemp);
        return regions;
    }

    @Override
    public List<SysRegionVo> findSysRegionList() {
        List<SysRegionVo> listTemp = baseMapper.selectSysRegionVoList();
        List<SysRegionVo> regions = getTreeNodeList("0", listTemp);
        return regions;
    }

    @Override
    public List<RegionVo> findRegionVo(QueryRegionCondition condition) {
        return baseMapper.selectRegionVo(condition);
    }

    @Override
    public List<String> findRegionThreeList(String childId) {
        SysRegionThreeVo vo = baseMapper.selectRegionThreeList(childId);
        List<String> listTemp = new ArrayList<>();
        listTemp.add(vo.getId1());
        listTemp.add(vo.getId2());
        listTemp.add(vo.getId3());
        return listTemp;
    }
}
