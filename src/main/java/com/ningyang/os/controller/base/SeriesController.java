package com.ningyang.os.controller.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.base.SeriesCommand;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.vo.web.base.SeriesVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.ISerBrandSeriesInfoService;
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
 * @Date：2018/11/13 14:28
 * @描述：系列库
 */
@RestController
@RequestMapping("base/series")
public class SeriesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesController.class);

    @Autowired
    private ISerBrandSeriesInfoService infoService;

    @GetMapping("getBrandSeriesPageList")
    public Map<String,Object> getBrandSeriesPageList(
            QueryBrandSeriesProductCondition condition
    ){
        try {
            Page<SeriesVo> pageVo = infoService.findSeriesVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }


    @GetMapping("getBrandSeriesList")
    public Map<String, Object> getBrandSeriesList(
            QueryBrandSeriesProductCondition condition
    ) {
        try {
            List<SeriesVo> listVo = infoService.findSeriesVoByCondition(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }


    @PostMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate(
            @RequestBody SeriesCommand command
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
