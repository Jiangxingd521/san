package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.base.QueryRegionCondition;
import com.ningyang.os.action.output.vo.web.base.RegionVo;
import com.ningyang.os.action.output.vo.web.base.SysRegionThreeVo;
import com.ningyang.os.action.output.vo.web.base.SysRegionVo;
import com.ningyang.os.pojo.SysBaseRegionInfo;

import java.util.List;

/**
 * <p>
 * 系统区域信息表 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface SysBaseRegionInfoMapper extends BaseMapper<SysBaseRegionInfo> {

    List<SysRegionVo> selectSysRegionVoList();

    List<RegionVo> selectRegionVo(QueryRegionCondition condition);

    SysRegionThreeVo selectRegionThreeList(String childId);
}
