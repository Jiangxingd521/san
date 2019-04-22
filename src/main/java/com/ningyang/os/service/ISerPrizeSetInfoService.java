package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.PrizeSetCommand;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetVo;
import com.ningyang.os.pojo.SerPrizeSetInfo;

import java.util.List;

/**
 * <p>
 * 奖项设定 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface ISerPrizeSetInfoService extends IService<SerPrizeSetInfo> {

    Page<PrizeSetVo> findPrizeSetVoListPageByCondition(QueryPrizeCondition condition);

    List<PrizeSetVo> findPrizeSetVoListByCondition(QueryPrizeCondition condition);

    boolean addOrUpdate(PrizeSetCommand command, Long userId);

    PrizeSetVo findPrizeSetVoById(Long prizeSetId);
}
