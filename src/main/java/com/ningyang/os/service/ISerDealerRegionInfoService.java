package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.pojo.SerDealerRegionInfo;

import java.util.List;

/**
 * <p>
 * 经销商区域 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-15
 */
public interface ISerDealerRegionInfoService extends IService<SerDealerRegionInfo> {

    List<String> getDealerRegionList(Long dealerId);

    String getDealerRegionName(Long dealerId);

}
