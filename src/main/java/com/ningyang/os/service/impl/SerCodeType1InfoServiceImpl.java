package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.output.vo.web.base.CodeTypeVo;
import com.ningyang.os.dao.SerCodeType1InfoMapper;
import com.ningyang.os.pojo.SerCodeType1Info;
import com.ningyang.os.service.ISerCodeType1InfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 内外码 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
@Service
public class SerCodeType1InfoServiceImpl extends ServiceImpl<SerCodeType1InfoMapper, SerCodeType1Info> implements ISerCodeType1InfoService {

    @Override
    public List<CodeTypeVo> findCodeTypeVoByCondition() {
        List<CodeTypeVo> voList = new ArrayList<>();
        List<SerCodeType1Info> listTemp = list(null);
        for (SerCodeType1Info info : listTemp) {
            CodeTypeVo vo = new CodeTypeVo();
            vo.setCodeId(info.getId());
            vo.setCodeName(info.getCodeName());
            voList.add(vo);
        }
        return voList;
    }
}
