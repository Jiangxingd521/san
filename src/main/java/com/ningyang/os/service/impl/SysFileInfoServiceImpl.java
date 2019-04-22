package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.output.dto.web.FileUploadDto;
import com.ningyang.os.dao.SysFileInfoMapper;
import com.ningyang.os.pojo.SysFileInfo;
import com.ningyang.os.service.ISysFileInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 上传文件信息 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-10-18
 */
@Service
public class SysFileInfoServiceImpl extends ServiceImpl<SysFileInfoMapper, SysFileInfo> implements ISysFileInfoService {

    @Override
    public FileUploadDto add(String filePath, String fileName) {
        SysFileInfo fileInfo = new SysFileInfo();
        fileInfo.setFileName(fileName);
        fileInfo.setFilePath(filePath);
        fileInfo.setCreateTime(new Date());
        fileInfo.setUpdateTime(new Date());
        save(fileInfo);
        FileUploadDto dto = new FileUploadDto();
        dto.setId(fileInfo.getId());
        dto.setUrl(filePath);
        dto.setName(fileName);
        dto.setUid(fileInfo.getId());
        dto.setStatus("success");
        return dto;
    }
}
