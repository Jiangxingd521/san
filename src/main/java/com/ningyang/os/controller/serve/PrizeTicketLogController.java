package com.ningyang.os.controller.serve;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeTicketLogVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.ISerPrizeRecodeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.DATA_ERROR;

/**
 * @Author： kaider
 * @Date：2018/12/04 10:26
 * @描述：兑奖记录
 */
@RestController
@RequestMapping("serve/prize/ticket/log")
public class PrizeTicketLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeTicketLogController.class);

    @Autowired
    private ISerPrizeRecodeInfoService infoService;

    @GetMapping("getPageList")
    public Map<String, Object> getPageList(
            QueryPrizeCondition condition
    ) {
        try {
            Page<PrizeTicketLogVo> pageVo = infoService.findPrizeTicketLogVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

}
