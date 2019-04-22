package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.base.QueryMenuCondition;
import com.ningyang.os.action.output.vo.web.base.BarVo;
import com.ningyang.os.pojo.SysNavigationBarInfo;

import java.util.List;

/**
 * <p>
 * 导航栏信息表 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface SysNavigationBarInfoMapper extends BaseMapper<SysNavigationBarInfo> {

    List<BarVo> selectBarVoListByCondition(QueryMenuCondition condition);

    List<BarVo> selectUserBarVoListByCondition(QueryMenuCondition condition);

    String selectUserWelTagByUserId(Long userId);

}
