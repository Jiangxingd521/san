package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.api.ApiWarehouseOrderDetailCommand;
import com.ningyang.os.action.input.command.api.ApiWarehouseSaleOrderCommand;
import com.ningyang.os.action.input.command.web.serve.OrderSaleCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.GoodsPutOutVo;
import com.ningyang.os.action.output.vo.web.serve.OrderDetailVo;
import com.ningyang.os.action.output.vo.web.serve.SaleOrderVo;
import com.ningyang.os.dao.SerOrderInfoMapper;
import com.ningyang.os.pojo.SerOrderInfo;
import com.ningyang.os.pojo.SerOrderInfoDetails;
import com.ningyang.os.service.ILSerWarehouseGoodsOutInfoService;
import com.ningyang.os.service.ISerOrderInfoDetailsService;
import com.ningyang.os.service.ISerOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.dateToDate;
import static com.ningyang.os.action.utils.DateUtil.getOrderNum;

/**
 * <p>
 * 销售订单表 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@Service
public class SerOrderInfoServiceImpl extends ServiceImpl<SerOrderInfoMapper, SerOrderInfo> implements ISerOrderInfoService {

    @Autowired
    private ISerOrderInfoDetailsService detailsService;
    @Autowired
    private ILSerWarehouseGoodsOutInfoService outInfoService;

    @Override
    public Page<SaleOrderVo> findSaleOrderVoPageByCondition(QueryOrderCondition condition) {
        Page<SaleOrderVo> pageVo = new Page<>();
        List<SaleOrderVo> listVoTemp = baseMapper.selectSaleOrderVoPageByCondition(condition);

        for (SaleOrderVo vo : listVoTemp) {
            QueryOrderCondition orderCondition = new QueryOrderCondition();
            orderCondition.setOrderId(vo.getOrderId());
            List<OrderDetailVo> detailList = detailsService.findOrderDetailVoList(orderCondition);
            vo.setDetailList(detailList);
            int boxCount = 0;
            for(OrderDetailVo detailVo : detailList){
                boxCount = boxCount+detailVo.getBoxNumber();
            }
            vo.setProductNumber(boxCount);
        }

        long total = baseMapper.selectSaleOrderVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean addOrUpdate(OrderSaleCommand command, Long operateUserId) {
        SerOrderInfo info = getById(command.getOrderId());
        boolean flag;
        if (info != null) {
            info.setDealerId(command.getDealerId());
            info.setProductId(command.getProductId());
            info.setIdata1(1);
            info.setOrderState(command.getOrderState());
            info.setOrderRemark(command.getRemark());
            if (command.getOperateType() == 1) {
                info.setFinancialId(operateUserId);
            }
            info.setUpdateTime(new Date());
            flag = updateById(info);
        } else {
            info = new SerOrderInfo();
            info.setOrderNo(getOrderNum());
            info.setDealerId(command.getDealerId());
            info.setProductId(command.getProductId());
            info.setIdata1(1);
            info.setOrderState(0);
            info.setOrderRemark(command.getRemark());
            info.setUserId(operateUserId);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag = save(info);
        }

        if (command.getDetailList().size() > 0) {
            List<SerOrderInfoDetails> detailsList = new ArrayList<>();
            detailsService.delete();
            for (OrderDetailVo vo : command.getDetailList()) {
                SerOrderInfoDetails details = new SerOrderInfoDetails();
                details.setOrderId(info.getId());
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
    public List<SaleOrderVo> findSaleOrderVoListByCondition(QueryOrderCondition condition) {
        List<SaleOrderVo> listTemp = baseMapper.selectSaleOrderVoListByCondition(condition);
        for (SaleOrderVo vo : listTemp) {
            vo.setOrderCreateTimeStr(dateToDate(vo.getOrderCreateTime()));
            //查询具体订单内容
            condition.setOrderId(vo.getOrderId());
            List<OrderDetailVo> detailList = detailsService.findOrderDetailVoList(condition);
            vo.setDetailList(detailList);
            //订单数量
            int boxCount = 0;
            for(OrderDetailVo detailVo : detailList){
                boxCount = boxCount+detailVo.getBoxNumber();
            }
            vo.setProductNumber(boxCount);
            //已出货数量
            int outBoxCount = outInfoService.getOrderOutBoxCount(vo.getOrderId());
            vo.setOutBoxCount(outBoxCount);

            if(outBoxCount>0){
                if(outBoxCount < boxCount){//已发货未完成
                    vo.setOrderState(1);
                }else{//已发货完成
                    vo.setOrderState(2);
                }
            }else{//未发货
                vo.setOrderState(0);
            }
        }
        return listTemp;
    }

    @Override
    public int getOrderBoxCount(QueryGoodsPutCondition condition) {
        QueryOrderCondition orderCondition = new QueryOrderCondition();
        orderCondition.setOrderId(condition.getOrderId());
        orderCondition.setProductId(condition.getProductId());
        return detailsService.boxCount(orderCondition);
    }

    @Override
    public boolean apiWareHouseAdd(ApiWarehouseSaleOrderCommand command) {
        SerOrderInfo info = new SerOrderInfo();
        info.setOrderNo(getOrderNum());
        info.setDealerId(command.getDealerId());
        info.setOrderState(1);
        info.setIdata1(2);
        info.setOrderRemark(command.getRemark());
        info.setUserId(command.getCreateUserId());
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        boolean flag = save(info);
        //插入订单明细
        if(command.getDetailList().size()>0){
            List<SerOrderInfoDetails> detailsList = new ArrayList<>();
            for(ApiWarehouseOrderDetailCommand detailCommand : command.getDetailList()){
                SerOrderInfoDetails details = new SerOrderInfoDetails();
                details.setOrderId(info.getId());
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

    @Override
    public List<SaleOrderVo> findOrderCompleteListByCondition(QueryOrderCondition condition) {
        List<SaleOrderVo> listTemp = baseMapper.selectOrderCompleteListByCondition(condition);
        for(SaleOrderVo vo : listTemp){
            vo.setOrderCreateTimeStr(dateToDate(vo.getOrderCreateTime()));
            vo.setOrderCompleteTimeStr(dateToDate(vo.getOrderCompleteTime()));
            condition.setOrderId(vo.getOrderId());
            List<OrderDetailVo> detailList = detailsService.findOrderDetailVoList(condition);
            //订单数量
            int boxCount = 0;
            for(OrderDetailVo detailVo : detailList){
                boxCount = boxCount+detailVo.getBoxNumber();
            }
            vo.setProductNumber(boxCount);
            vo.setOrderState(1);
            //已出货数量
            int outBoxCount = outInfoService.getOrderOutBoxCount(vo.getOrderId());
            vo.setOutBoxCount(outBoxCount);
            //出库明细
            QueryGoodsPutCondition putCondition = new QueryGoodsPutCondition();
            putCondition.setOrderId(vo.getOrderId());
            List<GoodsPutOutVo> warehouseDetailList = outInfoService.findWarehouseGoodsPutOutVoByCondition(putCondition);
            vo.setWarehouseDetailList(warehouseDetailList);
        }
        return listTemp;
    }

    @Override
    public SaleOrderVo getSaleOrderVoByOrderId(Long orderId) {
        SerOrderInfo info = getById(orderId);
        SaleOrderVo vo = new SaleOrderVo();
        vo.setDealerId(info.getDealerId());
        QueryOrderCondition orderCondition = new QueryOrderCondition();
        orderCondition.setOrderId(orderId);
        List<OrderDetailVo> detailList = detailsService.findOrderDetailVoList(orderCondition);
        vo.setDetailList(detailList);
        vo.setRemark(info.getOrderRemark());
        return vo;
    }

}
