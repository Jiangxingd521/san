package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.ApplyCodeCommand;
import com.ningyang.os.action.input.command.web.serve.CenterCodeCommand;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.output.vo.web.serve.ApplyCodeVo;
import com.ningyang.os.pojo.SerApplyCodeInfo;

/**
 * <p>
 * 溯源码申请 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-15
 */
public interface ISerApplyCodeInfoService extends IService<SerApplyCodeInfo> {

    Page<ApplyCodeVo> findApplyCodeVoPageByCondition(QueryCodeCondition condition);

    boolean add(ApplyCodeCommand command);

    boolean updateApplyState(CenterCodeCommand command);
}
