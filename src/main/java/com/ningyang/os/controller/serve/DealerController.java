package com.ningyang.os.controller.serve;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.serve.DealerCommand;
import com.ningyang.os.action.input.condition.serve.QueryDealerCondition;
import com.ningyang.os.action.output.vo.web.serve.DealerVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.ISerDealerInfoService;
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
 * @Date：2018/11/14 16:50
 * @描述：经销商管理
 */
@RestController
@RequestMapping("serve/dealer")
public class DealerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DealerController.class);

    @Autowired
    private ISerDealerInfoService infoService;


    @GetMapping("getDealerPage")
    public Map<String, Object> getDealerPage(
            QueryDealerCondition condition
    ) {
        try {
            Page<DealerVo> pageVo = infoService.findDealerVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    @PostMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate(
            @RequestBody DealerCommand command
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

    @GetMapping("getDealerList")
    public Map<String, Object> getDealerList() {
        try {
            List<DealerVo> listVo = infoService.findDealerVoListByCondition();
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    /**
     * 校验注册时是否已经含有社会代码
     *
     * @param command
     * @return
     */
    @PostMapping("checkDealerCode")
    public Map<String, Object> checkDealerCode(
            @RequestBody DealerCommand command
    ) {
        try {
            boolean flag = infoService.checkDealerCode(command);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure("社会代码已经存在").toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }
    /**
     * 校验该经销商是否有订单信息
     *
     * @return
     */
    @DeleteMapping ("isOrderInformation/{dealerId}")
    public Map<String, Object> isOrderInformation(@PathVariable("dealerId") Long dealerId) {
        try {
            boolean flag = infoService.isOrderInformation(dealerId);
            if (flag) {
                return WebResult.failure("").toMap();
            }
            return WebResult.success().toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }

    /**
     *
     *  删除经销商状态dealerId(1为已删除)
     * @return
     */
    @DeleteMapping ("deleteDealer/{dealerId}")
    public Map<String, Object> deleteDealer(@PathVariable("dealerId") Long dealerId) {
        try {
            boolean flag = infoService.deleteDealer(dealerId);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure("").toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }
}
