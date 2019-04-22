package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.CodeTypeCommand;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.output.vo.web.base.CodeTypeVo;
import com.ningyang.os.dao.SerCodeType3InfoMapper;
import com.ningyang.os.pojo.SerCodeType3Info;
import com.ningyang.os.service.ISerCodeType3InfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 溯源码类型 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
@Service
public class SerCodeType3InfoServiceImpl extends ServiceImpl<SerCodeType3InfoMapper, SerCodeType3Info> implements ISerCodeType3InfoService {

    @Override
    public List<CodeTypeVo> findCodeTypeVoByCondition(QueryCodeCondition condition) {
        return baseMapper.selectCodeTypeVoByCondition(condition);
    }

    @Override
    public boolean addOrUpdate(CodeTypeCommand command) {
        SerCodeType3Info info = getOne(new QueryWrapper<SerCodeType3Info>().eq("id", command.getCodeId()));
        boolean flag;
        if (info != null) {
            info.setCodeName(command.getCodeName());
            info.setCodeState(command.getCodeState());
            info.setUpdateTime(new Date());
            flag = updateById(info);
        } else {
            info = new SerCodeType3Info();
            info.setCodeName(command.getCodeName());
            info.setCodeState(0);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag = save(info);
        }
        return flag;
    }


}
