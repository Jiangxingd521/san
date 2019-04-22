package com.ningyang.os.controller.system.region;

import com.ningyang.os.action.input.condition.base.QueryRegionCondition;
import com.ningyang.os.action.output.vo.web.base.RegionVo;
import com.ningyang.os.action.output.vo.web.base.SysRegionVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.service.ISysBaseRegionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author： kaider
 * @Date：2018/11/12 18:05
 * @描述：系统区域
 */
@RestController
@RequestMapping("sys/region")
public class RegionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    private ISysBaseRegionInfoService infoService;

    @GetMapping("getRegionTree")
    public Map<String, Object> getRegionTree() {
        try {
            List<SysRegionVo> listVo = infoService.findSysRegionVoList();
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    @GetMapping("getRegionList")
    public Map<String, Object> getRegionList() {
        try {
            List<SysRegionVo> listVo = infoService.findSysRegionList();
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    @GetMapping("getRegion")
    public Map<String, Object> getRegion(
            QueryRegionCondition condition
    ) {
        try {
            List<RegionVo> listVo = infoService.findRegionVo(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

}
