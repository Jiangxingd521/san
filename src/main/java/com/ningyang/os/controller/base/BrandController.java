package com.ningyang.os.controller.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.base.BrandCommand;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.vo.web.base.BrandVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.ISerBrandInfoService;
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
 * @Date：2018/11/13 14:27
 * @描述：品牌库
 */
@RestController
@RequestMapping("base/brand")
public class BrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private ISerBrandInfoService infoService;

    @GetMapping("getBrandPageList")
    public Map<String,Object> getBrandPageList(
            QueryBrandSeriesProductCondition condition
    ){
        try {
            Page<BrandVo> pageVo = infoService.findBrandVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    @GetMapping("getBrandList")
    public Map<String, Object> getBrandList(
            QueryBrandSeriesProductCondition condition
    ) {
        try {
            List<BrandVo> listVo = infoService.findBrandVoByCondition(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    @PostMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate(
            @RequestBody BrandCommand command
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
