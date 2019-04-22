package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.config.SystemConfig;
import com.ningyang.os.action.input.command.web.serve.CenterCodeCommand;
import com.ningyang.os.action.input.command.web.serve.TemplateCodeCommand;
import com.ningyang.os.action.input.condition.serve.QueryApplyCodeCondition;
import com.ningyang.os.action.output.dto.serve.CenterCodeDto;
import com.ningyang.os.dao.SerApplyCodeTemplateMapper;
import com.ningyang.os.pojo.SerApplyCodeTemplate;
import com.ningyang.os.service.ISerApplyCodeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.getSimpleYearMonthShort;


/**
 * @Author： kaider
 * @Date：2018/11/20 15:18
 * @描述：
 */
@Service
public class SerApplyCodeTemplateServiceImpl extends ServiceImpl<SerApplyCodeTemplateMapper, SerApplyCodeTemplate> implements ISerApplyCodeTemplateService {

    @Autowired
    private SystemConfig config;

    @Override
    public boolean addBatch(CenterCodeCommand command) {
        List<SerApplyCodeTemplate> list = new ArrayList<>();
        for (CenterCodeDto dto : command.getCodeVoList()) {
            SerApplyCodeTemplate code = setQRCode(dto.getCenterCodeId(), dto.getCodeContent(), dto.getCodeOrder(), command.getCodePositionId(), command.getCodePositionTypeId());
            list.add(code);
        }
        TemplateCodeCommand codeCommand = new TemplateCodeCommand();
        codeCommand.setTableName(templateTableName());
        codeCommand.setList(list);
        return baseMapper.insertBatch(codeCommand);
    }


    @Override
    public List<SerApplyCodeTemplate> findCodeVoList(QueryApplyCodeCondition condition) {
        String tableName = config.getDefaultQRCodeTemplateTable();
        tableName = tableName.replace("dateTemplate", condition.getTableName());
        condition.setTableName(tableName);
        return baseMapper.selectCodeVoList(condition);
    }


    @Override
    public SerApplyCodeTemplate findCodeByTables(String codeTables, String codeContent) {
        QueryApplyCodeCondition condition = new QueryApplyCodeCondition();
        condition.setTableName(codeTables);
        condition.setCodeContent(codeContent);
        return baseMapper.selectCodeByTables(condition);
    }

    /**
     * 生成溯源码表名
     *
     * @return
     */
    private String templateTableName() {
        String tableName = config.getDefaultQRCodeTemplateTable();
        tableName = tableName.replace("dateTemplate", getSimpleYearMonthShort());
        return tableName;
    }


    /**
     * 生成溯源码对象
     *
     * @param centerId
     * @param codeContent
     * @param codeOrder
     * @return
     */
    private SerApplyCodeTemplate setQRCode(Long centerId, String codeContent, String codeOrder, int codePosition, int codePositionType) {
        SerApplyCodeTemplate code = new SerApplyCodeTemplate();
        code.setCenterId(centerId);
        code.setCodeOrder(codeOrder);
        code.setCodeContent(codeContent);
        code.setCodePosition(codePosition);
        code.setCodePositionType(codePositionType);
        code.setCreateTime(new Date());
        code.setUpdateTime(new Date());
        return code;
    }

}
