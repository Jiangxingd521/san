package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.PrizeSetRecordCommand;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetRecordVo;
import com.ningyang.os.pojo.SerPrizeSetRecordInfo;

/**
 * <p>
 * 奖项设定操作记录 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-12-24
 */
public interface ISerPrizeSetRecordInfoService extends IService<SerPrizeSetRecordInfo> {

    Page<PrizeSetRecordVo> findPrizeSetRecordVoPageByCondition(QueryPrizeCondition condition);

    boolean add(PrizeSetRecordCommand command, Long operateUserId);

    boolean stopSetRecordById(PrizeSetRecordCommand command, Long operateUserId);

}
