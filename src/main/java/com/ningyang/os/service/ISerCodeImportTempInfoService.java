package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.utils.ReadFileBackData;
import com.ningyang.os.pojo.SerCodeImportTempInfo;

import java.util.List;

/**
 * <p>
 * 溯源码导入临时表 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
public interface ISerCodeImportTempInfoService extends IService<SerCodeImportTempInfo> {

    boolean add(List<ReadFileBackData> listData, Long templateId);

    void callSetCode();

}
