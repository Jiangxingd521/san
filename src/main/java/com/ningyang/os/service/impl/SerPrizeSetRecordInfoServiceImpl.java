package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.PrizeSetRecordCommand;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetRecordVo;
import com.ningyang.os.action.output.vo.web.serve.SaleOrderVo;
import com.ningyang.os.dao.SerPrizeSetRecordInfoMapper;
import com.ningyang.os.pojo.SerPrizeRecodeInfo;
import com.ningyang.os.pojo.SerPrizeSetRecordInfo;
import com.ningyang.os.pojo.SerWarehouseGoodsInfo;
import com.ningyang.os.service.ISerPrizeRecodeInfoService;
import com.ningyang.os.service.ISerPrizeSetRecordInfoService;
import com.ningyang.os.service.ISerWarehouseGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.dateToDate;
import static com.ningyang.os.action.utils.UuidUtil.generateUUID;

/**
 * <p>
 * 奖项设定操作记录 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-12-24
 */
@Service
public class SerPrizeSetRecordInfoServiceImpl extends ServiceImpl<SerPrizeSetRecordInfoMapper, SerPrizeSetRecordInfo> implements ISerPrizeSetRecordInfoService {

    @Autowired
    private ISerPrizeRecodeInfoService recodeInfoService;
    @Autowired
    private ISerWarehouseGoodsInfoService warehouseGoodsInfoService;


    @Override
    public Page<PrizeSetRecordVo> findPrizeSetRecordVoPageByCondition(QueryPrizeCondition condition) {
        Page<PrizeSetRecordVo> pageVo = new Page<>();
        List<PrizeSetRecordVo> listVoTemp = baseMapper.selectPrizeSetRecordVoPageByCondition(condition);
        for (PrizeSetRecordVo vo : listVoTemp) {
            vo.setPrizeStartDateStr(dateToDate(vo.getPrizeStartDate()));
            vo.setPrizeEndDateStr(dateToDate(vo.getPrizeEndDate()));
            vo.setCreateTimeStr(dateToDate(vo.getCreateTime()));
            //某个产品的当前库存总量
            int count = warehouseGoodsInfoService.count(new QueryWrapper<SerWarehouseGoodsInfo>().eq("goods_state",2));
            vo.setWarehouseGoodCount(Long.valueOf(count));
        }
        long total = baseMapper.selectPrizeSetRecordVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean add(PrizeSetRecordCommand command, Long operateUserId) {
        List<SerPrizeSetRecordInfo> listTemp = new ArrayList<>();
        boolean addFlag;
        boolean updateFlag;
        if(command.getPrizeSetType()==0){//订单
            for(SaleOrderVo vo : command.getSaleOrderVoList()){
                String uuid = generateUUID();
                SerPrizeSetRecordInfo info = new SerPrizeSetRecordInfo();
                info.setOrderId(vo.getOrderId());
                info.setOrderNo(vo.getOrderNo());
                info.setPrizeSetId(command.getPrizeSetId());
                info.setPrizeSetType(command.getPrizeSetType());
                info.setPrizeSetUuid(uuid);
                info.setPrizeSetState(0);
                info.setCreateUserId(operateUserId);
                info.setCreateTime(new Date());
                info.setUpdateTime(new Date());
                listTemp.add(info);
            }
            addFlag = saveBatch(listTemp);

            List<SerPrizeSetRecordInfo> listUpdateTemp = new ArrayList<>();
            for(SaleOrderVo vo : command.getSaleOrderVoList()){
                SerPrizeSetRecordInfo info = getOne(new QueryWrapper<SerPrizeSetRecordInfo>()
                        .eq("order_id",vo.getOrderId()));
                // FIXME: 2018-12-25 统计受影响个数
                int prizeSetCount = recodeInfoService.count(new QueryWrapper<SerPrizeRecodeInfo>()
                        .eq("prize_set_uuid",info.getPrizeSetUuid()));
                info.setPrizeSetCount(prizeSetCount);

                listUpdateTemp.add(info);
            }
            updateFlag = saveOrUpdateBatch(listUpdateTemp);

        }else{//产品
            String uuid = generateUUID();
            SerPrizeSetRecordInfo info = new SerPrizeSetRecordInfo();
            info.setPrizeSetId(command.getPrizeSetId());
            info.setPrizeSetType(command.getPrizeSetType());
            info.setPrizeSetUuid(uuid);
            info.setPrizeSetState(0);
            info.setBrandId(command.getBrandId());
            info.setSeriesId(command.getSeriesId());
            info.setProductId(command.getProductId());
            info.setCreateUserId(operateUserId);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            addFlag = save(info);

            // FIXME: 2018-12-25 统计受影响个数
            int prizeSetCount = recodeInfoService.count(new QueryWrapper<SerPrizeRecodeInfo>()
                    .eq("prize_set_uuid",info.getPrizeSetUuid()));
            info.setPrizeSetCount(prizeSetCount);

            updateFlag = saveOrUpdate(info);

        }
        return addFlag && updateFlag;
    }

    @Override
    public boolean stopSetRecordById(PrizeSetRecordCommand command, Long operateUserId) {
        SerPrizeSetRecordInfo info = getById(command.getRecordId());
        info.setPrizeSetState(command.getPrizeSetState());
        info.setCreateUserId(operateUserId);
        info.setUpdateTime(new Date());
        return updateById(info);
    }
}
