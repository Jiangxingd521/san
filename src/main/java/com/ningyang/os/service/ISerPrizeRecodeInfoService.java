package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.PrizeSetLogCommand;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetLogVo;
import com.ningyang.os.action.output.vo.web.serve.PrizeTicketLogVo;
import com.ningyang.os.pojo.SerPrizeRecodeInfo;

/**
 * <p>
 * 布奖记录 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface ISerPrizeRecodeInfoService extends IService<SerPrizeRecodeInfo> {

    Page<PrizeSetLogVo> findPrizeSetLogVoPageByCondition(QueryPrizeCondition condition);

    boolean add(PrizeSetLogCommand command, Long userId);

    Page<PrizeTicketLogVo> findPrizeTicketLogVoPageByCondition(QueryPrizeCondition condition);

    boolean addMake(PrizeSetLogCommand command, Long userId);
}
