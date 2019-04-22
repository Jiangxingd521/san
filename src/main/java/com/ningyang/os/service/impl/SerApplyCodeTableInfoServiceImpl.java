package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.dao.SerApplyCodeTableInfoMapper;
import com.ningyang.os.pojo.SerApplyCodeTableInfo;
import com.ningyang.os.service.ISerApplyCodeTableInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.getSimpleYearShort;

/**
 * <p>
 * 溯源码申请对应的存放表 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-25
 */
@Service
public class SerApplyCodeTableInfoServiceImpl extends ServiceImpl<SerApplyCodeTableInfoMapper, SerApplyCodeTableInfo> implements ISerApplyCodeTableInfoService {

    @Override
    public boolean addCodeFlag(String codeFlag) {
        SerApplyCodeTableInfo info = new SerApplyCodeTableInfo();
        info.setCodeFlag(codeFlag);
        info.setCodeTableName("t_ser_apply_code_" + getSimpleYearShort() + codeFlag);
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        return save(info);
    }

    @Override
    public String findCodeTableList(String leftCodeFlag) {
        List<String> tableList = baseMapper.selectCodeTableList(leftCodeFlag);
        String tables = StringUtils.join(tableList, ",");
        return tables;
    }
}
