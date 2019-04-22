package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.DealerCommand;
import com.ningyang.os.action.input.condition.serve.QueryDealerCondition;
import com.ningyang.os.action.output.vo.web.serve.DealerVo;
import com.ningyang.os.pojo.SerDealerInfo;

import java.util.List;


/**
 * <p>
 * 供应商信息 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface ISerDealerInfoService extends IService<SerDealerInfo> {

    Page<DealerVo> findDealerVoPageByCondition(QueryDealerCondition condition);

    boolean addOrUpdate(DealerCommand command);

    List<DealerVo> findDealerVoListByCondition();

    boolean checkDealerCode(DealerCommand command);

    boolean isOrderInformation(Long dealerId);

    boolean deleteDealer(Long dealerId);
}
