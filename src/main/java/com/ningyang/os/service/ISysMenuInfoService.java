package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.MenuCommand;
import com.ningyang.os.action.input.condition.base.QueryMenuCondition;
import com.ningyang.os.action.output.vo.web.base.BarMenuVo;
import com.ningyang.os.action.output.vo.web.base.SysMenuVo;
import com.ningyang.os.pojo.SysMenuInfo;

import java.util.List;

/**
 * <p>
 * 菜单信息 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface ISysMenuInfoService extends IService<SysMenuInfo> {

    List<BarMenuVo> findUserBarMenuVoList(QueryMenuCondition condition);

    List<SysMenuVo> findSysMenuVoList(QueryMenuCondition condition);

    boolean add(MenuCommand command);

    boolean edit(MenuCommand command);

    boolean delete(Long menuId);
}
