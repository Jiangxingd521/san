package com.ningyang.os.controller.base;

import com.ningyang.os.action.input.command.web.base.CodeTypeCommand;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.output.vo.web.base.CodeTypeVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.ISerCodeType1InfoService;
import com.ningyang.os.service.ISerCodeType2InfoService;
import com.ningyang.os.service.ISerCodeType3InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.DATA_ERROR;
import static com.ningyang.os.action.enums.SystemErrorEnum.OPERATING_ERROR;

/**
 * @Author： kaider
 * @Date：2018/11/14 09:51
 * @描述：码类型
 */
@RestController
@RequestMapping("base/codeType")
public class CodeTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeTypeController.class);

    @Autowired
    private ISerCodeType1InfoService infoService1;
    @Autowired
    private ISerCodeType2InfoService infoService2;
    @Autowired
    private ISerCodeType3InfoService infoService3;

    /**
     * 内外码
     *
     * @return
     */
    @GetMapping("getBaseCodeType1List")
    public Map<String, Object> getBaseCodeType1List() {
        try {
            List<CodeTypeVo> listVo = infoService1.findCodeTypeVoByCondition();
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    /**
     * 条形码、二维码
     *
     * @return
     */
    @GetMapping("getBaseCodeType2List")
    public Map<String, Object> getBaseCodeType2List() {
        try {
            List<CodeTypeVo> listVo = infoService2.findCodeTypeVoByCondition();
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    /**
     * 盒内外码
     *
     * @param condition
     * @return
     */
    @GetMapping("getCodeTypeList")
    public Map<String, Object> getCodeTypeList(
            QueryCodeCondition condition
    ) {
        try {
            List<CodeTypeVo> listVo = infoService3.findCodeTypeVoByCondition(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    /**
     * @param command
     * @return
     */
    @PostMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate(
            @RequestBody CodeTypeCommand command
    ) {
        try {
            boolean flag = infoService3.addOrUpdate(command);
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
