package com.ningyang.os.controller.serve;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.serve.WarehouseCommand;
import com.ningyang.os.action.input.condition.serve.QueryWarehouseCondition;
import com.ningyang.os.action.output.vo.web.serve.WarehouseInventoryVo;
import com.ningyang.os.action.output.vo.web.serve.WarehousePersonVo;
import com.ningyang.os.action.output.vo.web.serve.WarehouseVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.ISerWarehouseInfoService;
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
 * @Date：2018/11/15 14:02
 * @描述：仓库管理
 */
@RestController
@RequestMapping("serve/warehouse")
public class WarehouseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);

    @Autowired
    private ISerWarehouseInfoService infoService;


    @GetMapping("getWarehousePage")
    public Map<String, Object> getWarehousePage(
            QueryWarehouseCondition condition
    ) {
        try {
            Page<WarehouseVo> pageVo = infoService.findWarehouseVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    @PostMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate(
            @RequestBody WarehouseCommand command
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

    @GetMapping("getWarehouseUserList")
    public Map<String, Object> getWarehouseUserList() {
        try {
            List<WarehousePersonVo> listVo = infoService.findWarehousePersonVoByCondition();
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    @GetMapping("getWarehouseInventory/{warehouseId}")
    public Map<String, Object> getWarehouseInventory(
            @PathVariable("warehouseId") Long warehouseId
    ){
        try {
            List<WarehouseInventoryVo> listVo = infoService.findWarehouseInventoryVoById(warehouseId);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

}
