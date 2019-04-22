package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.condition.base.QueryRegionCondition;
import com.ningyang.os.action.output.vo.web.base.RegionVo;
import com.ningyang.os.action.output.vo.web.base.SysRegionVo;
import com.ningyang.os.pojo.SysBaseRegionInfo;

import java.util.List;


/**
 * <p>
 * 系统区域信息表 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface ISysBaseRegionInfoService extends IService<SysBaseRegionInfo> {

    List<SysRegionVo> findSysRegionVoList();

    List<SysRegionVo> findSysRegionList();

    List<RegionVo> findRegionVo(QueryRegionCondition condition);

    List<String> findRegionThreeList(String childId);
}
