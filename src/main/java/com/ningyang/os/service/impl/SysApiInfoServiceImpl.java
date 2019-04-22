package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.RegisterCodeCommand;
import com.ningyang.os.action.output.dto.serve.RegisterCodeDto;
import com.ningyang.os.dao.SysApiInfoMapper;
import com.ningyang.os.pojo.SysApiInfo;
import com.ningyang.os.service.ISysApiInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 企业api标识 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-19
 */
@Service
public class SysApiInfoServiceImpl extends ServiceImpl<SysApiInfoMapper, SysApiInfo> implements ISysApiInfoService {

    @Override
    public boolean registerCode(RegisterCodeCommand command) {
        List<SysApiInfo> listTemp = list(null);

        if (listTemp.size() > 0) {
            return false;
        } else {
            List<SysApiInfo> apiInfoList = new ArrayList<>();
            for (RegisterCodeDto codeDto : command.getCodeVoList()) {
                SysApiInfo apiInfo = new SysApiInfo();
                apiInfo.setApiType(codeDto.getCodeType());
                apiInfo.setApiCode(codeDto.getCodeName());
                apiInfo.setCreateTime(new Date());
                apiInfo.setUpdateTime(new Date());
                apiInfoList.add(apiInfo);
            }
            return saveBatch(apiInfoList);
        }
    }

    @Override
    public boolean checkCode() {
        return list(null).size() > 0 ? true : false;
    }
}
