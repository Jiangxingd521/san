package com.ningyang.os.controller.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.base.CodeImportTemplateCommand;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.output.vo.web.base.CodeImportTemplateVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.ISerCodeImportTemplateInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.DATA_ERROR;
import static com.ningyang.os.action.enums.SystemErrorEnum.OPERATING_ERROR;

/**
 * @Author： kaider
 * @Date：2018/11/14 14:16
 * @描述：码模板
 */
@RestController
@RequestMapping("base/codeTemplate")
public class CodeTemplateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeTemplateController.class);

    @Autowired
    private ISerCodeImportTemplateInfoService infoService;


    @GetMapping("getCodeTemplatePageList")
    public Map<String, Object> getCodeTemplatePageList(
            QueryCodeCondition condition
    ) {
        try {
            Page<CodeImportTemplateVo> pageVo = infoService.findCodeImportVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    @PostMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate(
            @RequestBody CodeImportTemplateCommand command
    ) {
        try {
            boolean flag = infoService.addOrUpdate(command);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }

}
