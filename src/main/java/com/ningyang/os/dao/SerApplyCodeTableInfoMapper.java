package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.pojo.SerApplyCodeTableInfo;

import java.util.List;

/**
 * <p>
 * 溯源码申请对应的存放表 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-25
 */
public interface SerApplyCodeTableInfoMapper extends BaseMapper<SerApplyCodeTableInfo> {

    List<String> selectCodeTableList(String leftCodeFlag);

}
