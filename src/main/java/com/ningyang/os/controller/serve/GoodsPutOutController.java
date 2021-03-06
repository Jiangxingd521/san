package com.ningyang.os.controller.serve;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.output.vo.web.serve.GoodsPutOutVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.ILSerWarehouseGoodsOutInfoService;
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
 * @Date：2018/11/29 10:45
 * @描述：
 */
@RestController
@RequestMapping("serve/goodPutOut")
public class GoodsPutOutController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsPutOutController.class);

    @Autowired
    private ILSerWarehouseGoodsOutInfoService putOutService;

    @GetMapping("getPutOutPage")
    public Map<String, Object> getPutOutPage(
            QueryGoodsPutCondition condition
    ) {
        try {
            Page<GoodsPutOutVo> pageVo = putOutService.findGoodsPutOutVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

}
