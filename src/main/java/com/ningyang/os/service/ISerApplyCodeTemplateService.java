package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.CenterCodeCommand;
import com.ningyang.os.action.input.condition.serve.QueryApplyCodeCondition;
import com.ningyang.os.pojo.SerApplyCodeTemplate;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/20 15:17
 * @描述：
 */
public interface ISerApplyCodeTemplateService extends IService<SerApplyCodeTemplate> {

    boolean addBatch(CenterCodeCommand command);

    List<SerApplyCodeTemplate> findCodeVoList(QueryApplyCodeCondition condition);

    SerApplyCodeTemplate findCodeByTables(String codeTables, String codeContent);
}
