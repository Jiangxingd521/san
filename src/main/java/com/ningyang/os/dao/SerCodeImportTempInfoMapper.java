package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.pojo.SerCodeImportTempInfo;

/**
 * <p>
 * 溯源码导入临时表 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
public interface SerCodeImportTempInfoMapper extends BaseMapper<SerCodeImportTempInfo> {

    void callSetCode();

}
