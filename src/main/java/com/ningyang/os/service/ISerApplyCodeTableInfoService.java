package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.pojo.SerApplyCodeTableInfo;

/**
 * <p>
 * 溯源码申请对应的存放表 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-25
 */
public interface ISerApplyCodeTableInfoService extends IService<SerApplyCodeTableInfo> {

    boolean addCodeFlag(String codeFlag);

    String findCodeTableList(String leftCodeFlag);

}
