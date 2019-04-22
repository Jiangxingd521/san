package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.output.dto.web.FileUploadDto;
import com.ningyang.os.pojo.SysFileInfo;

/**
 * <p>
 * 上传文件信息 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-10-18
 */
public interface ISysFileInfoService extends IService<SysFileInfo> {

    FileUploadDto add(String filePath, String fileName);

}
