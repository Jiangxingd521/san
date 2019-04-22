package com.ningyang.os.controller.serve;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.serve.PrizeSetLogCommand;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetLogVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.service.ISerPrizeRecodeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.DATA_ERROR;
import static com.ningyang.os.action.enums.SystemErrorEnum.OPERATING_ERROR;

/**
 * @Author： kaider
 * @Date：2018/12/03 11:51
 * @描述：商品布奖设置记录
 */
@RestController
@RequestMapping("serve/prize/set/log")
public class PrizeSetLogController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeSetLogController.class);

    @Autowired
    private ISerPrizeRecodeInfoService infoService;

    @GetMapping("getPageList")
    public Map<String, Object> getPageList(
            QueryPrizeCondition condition
    ) {
        try {
            Page<PrizeSetLogVo> pageVo = infoService.findPrizeSetLogVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    @PostMapping("add")
    public Map<String, Object> addSale(
            @RequestHeader("Authorization") String userToken,
            @RequestBody PrizeSetLogCommand command
    ) {
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

    @PostMapping("addMake")
    public Map<String, Object> addMake(
            @RequestHeader("Authorization") String userToken,
            @RequestBody PrizeSetLogCommand command
    ){
        try {
            Long operateUserId = getBaseUserInfo(userToken).getId();
            boolean flag = infoService.addMake(command, operateUserId);
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
