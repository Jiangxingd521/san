package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.api.ApiWarehouseOrderDetailCommand;
import com.ningyang.os.action.input.command.api.ApiWarehouseSaleOrderCommand;
import com.ningyang.os.action.input.command.web.serve.OrderPurchaseCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.*;
import com.ningyang.os.dao.SerPurchaseOrderInfoMapper;
import com.ningyang.os.pojo.LSerWarehouseGoodsInfo;
import com.ningyang.os.pojo.SerOrderInfoDetails;
import com.ningyang.os.pojo.SerPurchaseOrderInfo;
import com.ningyang.os.pojo.SerPurchaseOrderInfoDetails;
import com.ningyang.os.service.ILSerWarehouseGoodsInfoService;
import com.ningyang.os.service.ISerPurchaseOrderInfoDetailsService;
import com.ningyang.os.service.ISerPurchaseOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.dateToDate;
import static com.ningyang.os.action.utils.DateUtil.getOrderNum;

/**
 * <p>
 * 商品退货订单 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@Service
public class SerPurchaseOrderInfoServiceImpl extends ServiceImpl<SerPurchaseOrderInfoMapper, SerPurchaseOrderInfo> implements ISerPurchaseOrderInfoService {

    @Autowired
    private ISerPurchaseOrderInfoDetailsService detailsService;
    @Autowired
    private ILSerWarehouseGoodsInfoService putInService;

    @Override
    public Page<OrderPurchaseVo> findOrderPurchaseVoPageByCondition(QueryOrderCondition condition) {
        Page<OrderPurchaseVo> pageVo = new Page<>();
        List<OrderPurchaseVo> listVoTemp = baseMapper.selectOrderPurchaseVoPageByCondition(condition);
        for (OrderPurchaseVo vo : listVoTemp) {
            //订单详情
            condition.setPurchaseId(vo.getPurchaseId());
            List<OrderDetailVo> detailList = detailsService.findOrderDetailVoList(condition);
            vo.setDetailList(detailList);
            //接收到的箱数
            vo.setReceiveBoxNumber(0);
            int boxCount = detailsService.boxCount(condition);
            vo.setProductNumber(boxCount);
        }
        long total = baseMapper.selectOrderPurchaseVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean addOrUpdate(OrderPurchaseCommand command, Long operateUserId) {
        SerPurchaseOrderInfo info = getById(command.getPurchaseId());
        boolean flag;
        if (info != null) {
            info.setDealerId(command.getDealerId());
            info.setOrderState(command.getOrderState());
            info.setOrderRemark(command.getRemark());
            if (command.getOperateType() == 1) {
                info.setFinancialId(operateUserId);
            }
            info.setUpdateTime(new Date());
            flag = updateById(info);
        } else {
            info = new SerPurchaseOrderInfo();
            info.setOrderNo(getOrderNum());
            info.setDealerId(command.getDealerId());
            info.setOrderState(0);
            info.setOrderRemark(command.getRemark());
            info.setUserId(operateUserId);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag = save(info);
        }

        if (command.getDetailList().size() > 0) {
            List<SerPurchaseOrderInfoDetails> detailsList = new ArrayList<>();
            detailsService.delete();
            for (OrderDetailVo vo : command.getDetailList()) {
                SerPurchaseOrderInfoDetails details = new SerPurchaseOrderInfoDetails();
                details.setPurchaseId(info.getId());
                details.setBoxNumber(vo.getBoxNumber());
                details.setProductId(vo.getProductId());
                details.setUserId(operateUserId);
                details.setCreateTime(new Date());
                details.setUpdateTime(new Date());
                detailsList.add(details);
            }
            detailsService.saveBatch(detailsList);
        }
        return flag;
    }

    @Override
    public List<OrderPurchaseVo> findOrderPurchaseVoListByCondition(QueryOrderCondition condition) {
        List<OrderPurchaseVo> listTemp = baseMapper.selectOrderPurchaseVoListByCondition(condition);
        for (OrderPurchaseVo vo : listTemp) {
            vo.setOrderCreateTimeStr(dateToDate(vo.getOrderCreateTime()));
            //查询具体订单内容
            condition.setPurchaseId(vo.getPurchaseId());
            List<OrderDetailVo> detailList = detailsService.findOrderDetailVoList(condition);
            vo.setDetailList(detailList);
            //订单数量
            int boxCount = 0;
            for(OrderDetailVo detailVo : detailList){
                boxCount = boxCount+detailVo.getBoxNumber();
            }
            vo.setProductNumber(boxCount);
            //已收货数量
            int inBoxCount = putInService.count(new QueryWrapper<LSerWarehouseGoodsInfo>()
                    .eq("source_type",1)
                    .eq("warehouse_in_no",vo.getOrderNo()));
            vo.setReceiveBoxNumber(inBoxCount);

            if(inBoxCount>0){
                if(inBoxCount < boxCount){//已收货未完成
                    vo.setOrderState(1);
                }else{//已收货完成
                    vo.setOrderState(2);
                }
            }else{//未收货
                vo.setOrderState(0);
            }
        }
        return listTemp;
    }

    @Override
    public List<OrderPurchaseVo> findOrderCompleteListByCondition(QueryOrderCondition condition) {
        List<OrderPurchaseVo> listTemp = baseMapper.selectOrderCompleteListByCondition();
        for(OrderPurchaseVo vo : listTemp){
            vo.setOrderCreateTimeStr(dateToDate(vo.getOrderCreateTime()));
            vo.setOrderCompleteTimeStr(dateToDate(vo.getOrderCompleteTime()));
            condition.setPurchaseId(vo.getPurchaseId());
            List<OrderDetailVo> detailList = detailsService.findOrderDetailVoList(condition);
            //订单数量
            int boxCount = 0;
            for(OrderDetailVo detailVo : detailList){
                boxCount = boxCount+detailVo.getBoxNumber();
            }
            vo.setProductNumber(boxCount);
            vo.setOrderState(1);
            //已收货数量
            int inBoxCount = putInService.count(new QueryWrapper<LSerWarehouseGoodsInfo>()
                    .eq("source_type",1)
                    .eq("warehouse_in_no",vo.getOrderNo()));
            vo.setReceiveBoxNumber(inBoxCount);
            //入库明细
            QueryGoodsPutCondition putCondition = new QueryGoodsPutCondition();
            putCondition.setPurchaseId(vo.getPurchaseId());
            List<GoodsPutInVo> warehouseDetailList = putInService.findGoodsPutInVoByCondition(putCondition);
            vo.setWarehouseDetailList(warehouseDetailList);
        }
        return listTemp;
    }

    @Override
    public int getOrderBoxCount(QueryGoodsPutCondition condition) {
        QueryOrderCondition orderCondition = new QueryOrderCondition();
        orderCondition.setPurchaseId(condition.getPurchaseId());
        orderCondition.setProductId(condition.getProductId());
        return detailsService.boxCount(orderCondition);
    }

    @Override
    public boolean apiWareHouseAdd(ApiWarehouseSaleOrderCommand command) {
        SerPurchaseOrderInfo info = new SerPurchaseOrderInfo();
        info.setDealerId(command.getDealerId());
        info.setOrderNo(getOrderNum());
        info.setOrderState(1);
        info.setOrderRemark(command.getRemark());
        info.setUserId(command.getCreateUserId());
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        boolean flag = save(info);
        if(command.getDetailList().size()>0){
            List<SerPurchaseOrderInfoDetails> detailsList = new ArrayList<>();
            for(ApiWarehouseOrderDetailCommand detailCommand : command.getDetailList()){
                SerPurchaseOrderInfoDetails details = new SerPurchaseOrderInfoDetails();
                details.setPurchaseId(info.getId());
                details.setBoxNumber(detailCommand.getBoxNumber());
                details.setUserId(command.getCreateUserId());
                details.setProductId(detailCommand.getProductId());
                details.setCreateTime(new Date());
                details.setUpdateTime(new Date());
                detailsList.add(details);
            }
            detailsService.saveBatch(detailsList);
        }
        return flag;
    }
}
