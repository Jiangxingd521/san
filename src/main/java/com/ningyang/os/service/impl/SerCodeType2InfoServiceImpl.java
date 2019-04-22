package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.output.vo.web.base.CodeTypeVo;
import com.ningyang.os.dao.SerCodeType2InfoMapper;
import com.ningyang.os.pojo.SerCodeType2Info;
import com.ningyang.os.service.ISerCodeType2InfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 条形码二维码 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
@Service
public class SerCodeType2InfoServiceImpl extends ServiceImpl<SerCodeType2InfoMapper, SerCodeType2Info> implements ISerCodeType2InfoService {

    @Override
    public List<CodeTypeVo> findCodeTypeVoByCondition() {
        List<CodeTypeVo> voList = new ArrayList<>();
        List<SerCodeType2Info> listTemp = list(null);
        for (SerCodeType2Info info : listTemp) {
            CodeTypeVo vo = new CodeTypeVo();
            vo.setCodeId(info.getId());
            vo.setCodeName(info.getCodeName());
            voList.add(vo);
        }
        return voList;
    }

}
