package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.base.QueryMenuCondition;
import com.ningyang.os.action.output.vo.web.base.BarMenuVo;
import com.ningyang.os.action.output.vo.web.base.SysMenuVo;
import com.ningyang.os.pojo.SysMenuInfo;

import java.util.List;

/**
 * <p>
 * 菜单信息 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface SysMenuInfoMapper extends BaseMapper<SysMenuInfo> {

    List<String> selectByUserId(String userId);

    List<BarMenuVo> selectUserBarMenuVoList(QueryMenuCondition condition);

    List<SysMenuVo> selectSysMenuVoList(QueryMenuCondition condition);

}
