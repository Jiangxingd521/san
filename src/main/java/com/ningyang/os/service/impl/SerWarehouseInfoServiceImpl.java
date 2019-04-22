package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.WarehouseCommand;
import com.ningyang.os.action.input.condition.serve.QueryWarehouseCondition;
import com.ningyang.os.action.output.vo.web.serve.WarehouseInventoryVo;
import com.ningyang.os.action.output.vo.web.serve.WarehousePersonVo;
import com.ningyang.os.action.output.vo.web.serve.WarehouseVo;
import com.ningyang.os.dao.SerWarehouseInfoMapper;
import com.ningyang.os.pojo.SerWarehouseGoodsInfo;
import com.ningyang.os.pojo.SerWarehouseInfo;
import com.ningyang.os.service.ILSerWarehouseGoodsInfoService;
import com.ningyang.os.service.ILSerWarehouseGoodsOutInfoService;
import com.ningyang.os.service.ISerWarehouseGoodsInfoService;
import com.ningyang.os.service.ISerWarehouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 仓库信息 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@Service
public class SerWarehouseInfoServiceImpl extends ServiceImpl<SerWarehouseInfoMapper, SerWarehouseInfo> implements ISerWarehouseInfoService {

    @Autowired
    private ILSerWarehouseGoodsInfoService inInfoService;
    @Autowired
    private ILSerWarehouseGoodsOutInfoService outInfoService;
    @Autowired
    private ISerWarehouseGoodsInfoService goodsInfoService;

    @Override
    public Page<WarehouseVo> findWarehouseVoPageByCondition(QueryWarehouseCondition condition) {
        Page<WarehouseVo> pageVo = new Page<>();
        List<WarehouseVo> listVoTemp = baseMapper.selectWarehouseVoPageByCondition(condition);
        //仓库库存量变化
        for (WarehouseVo vo : listVoTemp) {
            int boxCount = goodsInfoService.count(new QueryWrapper<SerWarehouseGoodsInfo>()
                    .eq("warehouse_id",vo.getWarehouseId()).eq("goods_state",1));
            vo.setUsedTotalInventory(boxCount);
        }
        long total = baseMapper.selectWarehouseVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean addOrUpdate(WarehouseCommand command) {
        SerWarehouseInfo info = getOne(new QueryWrapper<SerWarehouseInfo>().eq("id", command.getWarehouseId()));
        boolean flag;
        if (info != null) {
            info.setWarehouseName(command.getWarehouseName());
            info.setUserId(command.getWarehouseUserId());
            info.setWarehousePerson(command.getWarehousePerson());
            info.setWarehousePersonMobile(command.getWarehousePersonMobile());
            info.setTotalInventory(command.getTotalInventory());
            info.setWarehouseRemark(command.getWarehouseRemark());
            info.setWarehouseState(command.getWarehouseState());
            info.setUpdateTime(new Date());
            flag = updateById(info);
        } else {
            info = new SerWarehouseInfo();
            info.setWarehouseName(command.getWarehouseName());
            info.setUserId(command.getWarehouseUserId());
            info.setWarehousePerson(command.getWarehousePerson());
            info.setWarehousePersonMobile(command.getWarehousePersonMobile());
            info.setTotalInventory(command.getTotalInventory());
            info.setWarehouseRemark(command.getWarehouseRemark());
            info.setWarehouseState(0);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag = save(info);
        }
        return flag;
    }

    @Override
    public List<WarehousePersonVo> findWarehousePersonVoByCondition() {
        return baseMapper.selectWarehousePersonVoByCondition();
    }

    @Override
    public List<WarehouseVo> findWarehouseVoListByCondition(QueryWarehouseCondition condition) {
        List<WarehouseVo> listVoTemp = baseMapper.selectWarehouseVoListByCondition(condition);
        //仓库库存量变化
        for (WarehouseVo vo : listVoTemp) {
            //入库
            int inCount = inInfoService.getWarehouseBoxCount(vo.getWarehouseId());
            //出库
            int outCount = outInfoService.getWarehouseBoxCount(vo.getWarehouseId());
            vo.setUsedTotalInventory(inCount - outCount);
        }
        return listVoTemp;
    }

    @Override
    public List<WarehouseInventoryVo> findWarehouseInventoryVoById(Long warehouseId) {
        List<WarehouseInventoryVo> listVoTemp = inInfoService.findWarehouseInventoryVoById(warehouseId);
        return listVoTemp;
    }
}
