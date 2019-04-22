package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.BarCommand;
import com.ningyang.os.action.input.condition.base.QueryMenuCondition;
import com.ningyang.os.action.output.vo.web.base.BarVo;
import com.ningyang.os.pojo.SysNavigationBarInfo;

import java.util.List;

/**
 * <p>
 * 导航栏信息表 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
public interface ISysNavigationBarInfoService extends IService<SysNavigationBarInfo> {

    List<BarVo> findBarVoListByCondition(QueryMenuCondition condition);

    List<BarVo> findUserBarVoListByCondition(QueryMenuCondition condition);

    boolean add(BarCommand command);

    boolean edit(BarCommand command);

    String findUserWelTagByUserId(Long userId);

}
