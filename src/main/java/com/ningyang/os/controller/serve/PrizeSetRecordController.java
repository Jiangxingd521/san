package com.ningyang.os.controller.serve;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.serve.PrizeSetRecordCommand;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetRecordVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.service.ISerPrizeSetRecordInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.DATA_ERROR;
import static com.ningyang.os.action.enums.SystemErrorEnum.OPERATING_ERROR;

/**
 * @Author： kaider
 * @Date：2018/11/29 18:24
 * @描述：奖项设定操作
 */
@RestController
@RequestMapping("serve/prize/set/record")
public class PrizeSetRecordController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeSetRecordController.class);

    @Autowired
    private ISerPrizeSetRecordInfoService infoService;

    @GetMapping("getSetRecordPage")
    public Map<String,Object> getSetRecordPage(
            QueryPrizeCondition condition
    ){
        try {
            Page<PrizeSetRecordVo> pageVo = infoService.findPrizeSetRecordVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    @PostMapping("addSetRecord")
    public Map<String,Object> addSetRecord(
            @RequestHeader("Authorization") String userToken,
            @RequestBody PrizeSetRecordCommand command
    ){
        try {
            Long operateUserId = getBaseUserInfo(userToken).getId();
            boolean flag = infoService.add(command, operateUserId);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }

    @PostMapping("stopSetRecord")
    public Map<String,Object> stopSetRecord(
            @RequestHeader("Authorization") String userToken,
            @RequestBody PrizeSetRecordCommand command
    ){
        try {
            Long operateUserId = getBaseUserInfo(userToken).getId();
            boolean flag = infoService.stopSetRecordById(command, operateUserId);
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
