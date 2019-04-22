package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.PrizeManagerCommand;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeManagerVo;
import com.ningyang.os.pojo.SerPrizeManagerInfo;

import java.util.List;

/**
 * <p>
 * 奖项管理 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface ISerPrizeManagerInfoService extends IService<SerPrizeManagerInfo> {

    List<PrizeManagerVo> findPrizeManagerVoListByCondition(QueryPrizeCondition condition);
    Page<PrizeManagerVo> findPrizeManagerVoPageByCondition(QueryPrizeCondition condition);
    boolean addOrUpdate(PrizeManagerCommand command, Long userId);
    PrizeManagerVo findPrizeManagerVoById(Long managerId);
}
