package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.WarehouseCommand;
import com.ningyang.os.action.input.condition.serve.QueryWarehouseCondition;
import com.ningyang.os.action.output.vo.web.serve.WarehouseInventoryVo;
import com.ningyang.os.action.output.vo.web.serve.WarehousePersonVo;
import com.ningyang.os.action.output.vo.web.serve.WarehouseVo;
import com.ningyang.os.pojo.SerWarehouseInfo;

import java.util.List;

/**
 * <p>
 * 仓库信息 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface ISerWarehouseInfoService extends IService<SerWarehouseInfo> {

    Page<WarehouseVo> findWarehouseVoPageByCondition(QueryWarehouseCondition condition);

    boolean addOrUpdate(WarehouseCommand command);

    List<WarehousePersonVo> findWarehousePersonVoByCondition();

    List<WarehouseVo> findWarehouseVoListByCondition(QueryWarehouseCondition condition);

    List<WarehouseInventoryVo> findWarehouseInventoryVoById(Long warehouseId);

}
