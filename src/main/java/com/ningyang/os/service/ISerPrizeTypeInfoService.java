package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.PrizeTypeCommand;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeTypeVo;
import com.ningyang.os.pojo.SerPrizeTypeInfo;

import java.util.List;

/**
 * <p>
 * 奖项类型 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
public interface ISerPrizeTypeInfoService extends IService<SerPrizeTypeInfo> {

    Page<PrizeTypeVo> selectPrizeTypeVoPageByCondition(QueryPrizeCondition condition);
    List<PrizeTypeVo> selectPrizeTypeVoListByCondition(QueryPrizeCondition condition);
    boolean addOrUpdate(PrizeTypeCommand command, Long userId);

}
