package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.api.ApiWarehouseOrderReturnPutInCommand;
import com.ningyang.os.action.input.command.api.ApiWarehousePutInCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.output.dto.serve.ApiWarehouseLOutDetailsDtoTemp;
import com.ningyang.os.action.output.dto.serve.ApiWarehouseOutDetailsDtoTemp;
import com.ningyang.os.action.output.dto.serve.PutOutDto;
import com.ningyang.os.action.output.vo.api.ApiWarehouseGoodsInfoVo;
import com.ningyang.os.action.output.vo.api.ApiWarehouseGoodsVo;
import com.ningyang.os.action.output.vo.web.serve.GoodsPutInVo;
import com.ningyang.os.action.output.vo.web.serve.WarehouseInventoryVo;
import com.ningyang.os.dao.LSerWarehouseGoodsInfoMapper;
import com.ningyang.os.pojo.*;
import com.ningyang.os.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.ningyang.os.action.utils.DateUtil.getOrderNum;
import static com.ningyang.os.action.utils.DateUtil.timeToStr;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * <p>
 * 商品入库记录日志 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@Service
public class LSerWarehouseGoodsInfoServiceImpl extends ServiceImpl<LSerWarehouseGoodsInfoMapper, LSerWarehouseGoodsInfo> implements ILSerWarehouseGoodsInfoService {

    @Autowired
    private ISerGoodsInfoService goodsInfoService;
    @Autowired
    private ISerWarehouseGoodsInfoService warehouseGoodsInfoService;
    @Autowired
    private ISerPurchaseOrderInfoService purchaseOrderInfoService;
    @Autowired
    private ILSerWarehouseGoodsOutInfoService outInfoService;
    @Autowired
    private ISerPurchaseOrderInfoDetailsService detailsService;
    @Autowired
    private ISerBrandSeriesProductInfoService productInfoService;


    @Override
    public Map<String, Object> add(ApiWarehousePutInCommand command) {
        List<LSerWarehouseGoodsInfo> safeList = new ArrayList<>();
        List<LSerWarehouseGoodsInfo> unSafeList = new ArrayList<>();
        String orderNum = getOrderNum();
        for (String boxNo : command.getBoxCode()) {
            LSerWarehouseGoodsInfo info;
            //校验是否存在此商品
            SerGoodsInfo goodsInfo = goodsInfoService.getOne(new QueryWrapper<SerGoodsInfo>().eq("M5", boxNo));
            //校验码是否已经已经入库
            List<LSerWarehouseGoodsInfo> listDataTemp = list(new QueryWrapper<LSerWarehouseGoodsInfo>().eq("box_no", boxNo));
            if (listDataTemp.size() > 0) {
                info = getOne(new QueryWrapper<LSerWarehouseGoodsInfo>().eq("box_no", boxNo));
                unSafeList.add(info);
            } else if (goodsInfo == null) {
                LSerWarehouseGoodsInfo infoTemp = new LSerWarehouseGoodsInfo();
                infoTemp.setBoxNo(boxNo);
                unSafeList.add(infoTemp);
            } else {
                info = new LSerWarehouseGoodsInfo();
                info.setSourceType(0);
                info.setWarehouseId(command.getWarehouseId());
                info.setBoxNo(boxNo);
                info.setProductId(goodsInfo.getBrandSeriesProductId());
                info.setWarehouseInNo(orderNum);
                info.setUserId(command.getUserId());
                info.setWarehouseInTime(new Date());
                info.setRemark(command.getRemark());
                info.setCreateTime(new Date());
                info.setUpdateTime(new Date());
                safeList.add(info);
            }
        }

        //去除数组里面的重复对象
        List<LSerWarehouseGoodsInfo> saveListTemp = safeList.stream().collect(
                collectingAndThen(toCollection(() -> new TreeSet<>(comparing(LSerWarehouseGoodsInfo::getBoxNo))), ArrayList::new)
        );

        //去除数组里面的重复对象
        List<LSerWarehouseGoodsInfo> unSaveListTemp = unSafeList.stream().collect(
                collectingAndThen(toCollection(() -> new TreeSet<>(comparing(LSerWarehouseGoodsInfo::getBoxNo))), ArrayList::new)
        );

        boolean addFlag = saveBatch(saveListTemp);
        Map<String, Object> map = new HashMap<>();
        map.put("saveFlag", addFlag);
        map.put("unSaveFlag", unSaveListTemp);

        return map;
    }

    @Override
    public Page<GoodsPutInVo> findGoodsPutInVoPageByCondition(QueryGoodsPutCondition condition) {
        Page<GoodsPutInVo> pageVo = new Page<>();
        List<GoodsPutInVo> listVoTemp = baseMapper.selectGoodsPutInVoPageByCondition(condition);
        for (GoodsPutInVo vo : listVoTemp) {
            vo.setCreateTimeStr(timeToStr(vo.getCreateTime()));
        }
        long total = baseMapper.selectGoodsPutInVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public int getWarehouseBoxCount(Long warehouseId) {
        return baseMapper.getWarehouseBoxCount(warehouseId);
    }

    @Override
    public List<ApiWarehouseGoodsVo> findApiWarehouseGoodsVo(String productName) {
        List<ApiWarehouseGoodsVo> listTemp = baseMapper.selectApiWarehouseGoodsVo(productName);
        for(ApiWarehouseGoodsVo vo : listTemp){
            //当前出库此商品数量
            Long productId = vo.getProductId();
            int boxCount = warehouseGoodsInfoService.getWarehouseGoodsCount(productId);
            vo.setBoxCount(boxCount);
            //加入入库详细数据
            List<ApiWarehouseGoodsInfoVo> goodsInfoVoList = baseMapper.selectApiWarehouseGoodsInfoVo(productId);
            vo.setGoodsInfoVoList(goodsInfoVoList);
            for(ApiWarehouseGoodsInfoVo voTemp : goodsInfoVoList){
                voTemp.setWarehouseInTimeStr(timeToStr(voTemp.getWarehouseInTime()));
            }
        }
        return listTemp;
    }

    @Override
    public List<WarehouseInventoryVo> findWarehouseInventoryVoById(Long warehouseId) {
        return baseMapper.selectWarehouseInventoryVoById(warehouseId);
    }

    @Override
    public Map<String, Object> orderReturn(ApiWarehouseOrderReturnPutInCommand command) {
        Map<String, Object> map = new HashMap<>();
        QueryGoodsPutCondition condition = new QueryGoodsPutCondition();
        condition.setPurchaseId(command.getPurchaseId());
        //退货订单箱数
        int orderBoxCount = purchaseOrderInfoService.getOrderBoxCount(condition);
        //扫码箱数
        int scanBoxCount = command.getBoxCode().length;
        //已收箱数
        int inBoxCount = getOrderInBoxCount(command.getPurchaseId());
        //实际需要收箱数
        int actualBoxCount = orderBoxCount - inBoxCount;

        if(scanBoxCount > actualBoxCount){
            PutOutDto dto = new PutOutDto();
            dto.setFlag(false);
            dto.setMessage("扫码箱数大于订单箱数");
            map.put("putOutFlag", dto);
            return map;
        }else{
            List<LSerWarehouseGoodsInfo> safeList = new ArrayList<>();
            List<LSerWarehouseGoodsInfo> unSafeList1 = new ArrayList<>();
            List<LSerWarehouseGoodsInfo> unSafeList2 = new ArrayList<>();
            //退货订单信息
            SerPurchaseOrderInfo purchaseOrderInfo = purchaseOrderInfoService.getById(command.getPurchaseId());
            //退货订单经销商
            Long dealerId = purchaseOrderInfo.getDealerId();
            String orderNum = getOrderNum();

            for(String boxNo : command.getBoxCode()){
                //校验商品是否存在仓库
                LSerWarehouseGoodsInfo info = getOne(new QueryWrapper<LSerWarehouseGoodsInfo>().eq("box_no",boxNo));
                if(info==null){
                    LSerWarehouseGoodsInfo infoTemp = new LSerWarehouseGoodsInfo();
                    infoTemp.setBoxNo(boxNo);
                    unSafeList1.add(infoTemp);
                }else{
                    //校验商品是否是该订单经销商的货品
                    LSerWarehouseGoodsOutInfo outInfo = outInfoService.getOne(new QueryWrapper<LSerWarehouseGoodsOutInfo>()
                            .eq("box_no",boxNo).eq("dealer_id",dealerId));
                    if(outInfo==null){
                        LSerWarehouseGoodsInfo infoTemp = new LSerWarehouseGoodsInfo();
                        infoTemp.setBoxNo(boxNo);
                        unSafeList2.add(infoTemp);
                    }else{
                        //待入库商品
                        info.setSourceType(1);
                        info.setPurchaseId(command.getPurchaseId());
                        info.setWarehouseId(command.getWarehouseId());
                        info.setWarehouseInNo(orderNum);
                        info.setUserId(command.getUserId());
                        info.setRemark(command.getRemark());
                        info.setUpdateTime(new Date());
                        safeList.add(info);
                    }
                }
            }

            List<LSerWarehouseGoodsInfo> unSaveListTemp1 = unSafeList1.stream().collect(
                    collectingAndThen(toCollection(() -> new TreeSet<>(comparing(LSerWarehouseGoodsInfo::getBoxNo))), ArrayList::new)
            );
            List<LSerWarehouseGoodsInfo> unSaveListTemp2 = unSafeList2.stream().collect(
                    collectingAndThen(toCollection(() -> new TreeSet<>(comparing(LSerWarehouseGoodsInfo::getBoxNo))), ArrayList::new)
            );
            if(unSaveListTemp1.size()>0){
                PutOutDto dto = new PutOutDto();
                dto.setFlag(false);
                dto.setMessage("存在未入库的商品");
                dto.setObj(unSaveListTemp1);
                map.put("putOutFlag", dto);
                return map;
            }else if(unSaveListTemp2.size()>0){
                PutOutDto dto = new PutOutDto();
                dto.setFlag(false);
                dto.setMessage("存在其他经销商的商品");
                dto.setObj(unSaveListTemp2);
                map.put("putOutFlag", dto);
                return map;
            }

            //去除数组里面的重复对象(扫描到的可能正确的商品)
            List<LSerWarehouseGoodsInfo> saveListTemp = safeList.stream().collect(
                    collectingAndThen(toCollection(() -> new TreeSet<>(comparing(LSerWarehouseGoodsInfo::getBoxNo))), ArrayList::new)
            );

            //查看订单详情
            List<SerPurchaseOrderInfoDetails> detailsList = detailsService.list(new QueryWrapper<SerPurchaseOrderInfoDetails>()
                    .eq("purchase_id", command.getPurchaseId()));

            //退货订单详情
            Map<Long, List<SerPurchaseOrderInfoDetails>> groupByDetails = detailsList.stream().collect(Collectors
                    .groupingBy(SerPurchaseOrderInfoDetails::getProductId));
            //扫描到的商品详情
            Map<Long, List<LSerWarehouseGoodsInfo>> groupByGoods = saveListTemp.stream().collect(Collectors
                    .groupingBy(LSerWarehouseGoodsInfo::getProductId));

            //订单明细
            List<ApiWarehouseOutDetailsDtoTemp> orderDetailsDtoTempList = new ArrayList<>();
            for (Map.Entry<Long, List<SerPurchaseOrderInfoDetails>> entry : groupByDetails.entrySet()) {
//                System.out.println("键 key ："+entry.getKey()+" 值value ："+entry.getValue());
                ApiWarehouseOutDetailsDtoTemp temp = new ApiWarehouseOutDetailsDtoTemp();
                temp.setProductId(entry.getKey());
                temp.setBoxNumber(entry.getValue().get(0).getBoxNumber());
                orderDetailsDtoTempList.add(temp);
            }
            //扫描到的商品明细
            List<ApiWarehouseOutDetailsDtoTemp> scanDetailsDtoTempList = new ArrayList<>();
            for (Map.Entry<Long, List<LSerWarehouseGoodsInfo>> entry : groupByGoods.entrySet()) {
//                System.out.println("键 key ："+entry.getKey()+"箱数："+entry.getValue().size()+" 值value ："+entry.getValue());
                ApiWarehouseOutDetailsDtoTemp temp = new ApiWarehouseOutDetailsDtoTemp();
                temp.setProductId(entry.getKey());
                temp.setBoxNumber(entry.getValue().size());
                scanDetailsDtoTempList.add(temp);
            }
            //校验扫到的箱数
            List<ApiWarehouseOutDetailsDtoTemp> checkTempList = new ArrayList<>();
            for (ApiWarehouseOutDetailsDtoTemp outDetailsDtoTemp : orderDetailsDtoTempList) {
                for (ApiWarehouseOutDetailsDtoTemp scanDetailsDtoTemp : scanDetailsDtoTempList) {
                    if (outDetailsDtoTemp.getProductId() == scanDetailsDtoTemp.getProductId()) {
                        if (scanDetailsDtoTemp.getBoxNumber() > outDetailsDtoTemp.getBoxNumber()) {
                            checkTempList.add(scanDetailsDtoTemp);
                            continue;
                        }
                    }
                }
            }

            if (checkTempList.size() > 0) {
                PutOutDto dto = new PutOutDto();
                dto.setFlag(false);
                dto.setMessage("扫码产品箱数大于订单产品箱数");
                dto.setObj(saveListTemp);
                map.put("putOutFlag", dto);
                return map;
            }

            //查询已经收到的各商品箱数
            List<LSerWarehouseGoodsInfo> inDetailsDtoTempList = list(new QueryWrapper<LSerWarehouseGoodsInfo>()
                    .eq("purchase_id", command.getPurchaseId()));

            if(inDetailsDtoTempList.size()>0){
                Map<Long, List<LSerWarehouseGoodsInfo>> groupByInGoods = inDetailsDtoTempList.stream().collect(Collectors
                        .groupingBy(LSerWarehouseGoodsInfo::getProductId));

                List<ApiWarehouseLOutDetailsDtoTemp> LInDetailsDtoTempList = new ArrayList<>();

                for (Map.Entry<Long, List<LSerWarehouseGoodsInfo>> entry : groupByInGoods.entrySet()) {
                    //已经收货的系列产品id
                    Long productId = entry.getKey();
                    //判断与扫描到的箱子
                    for (ApiWarehouseOutDetailsDtoTemp scanDetailsDtoTemp : scanDetailsDtoTempList) {
                        if (scanDetailsDtoTemp.getProductId().equals(productId)) {
                            //与订单具体
                            for (ApiWarehouseOutDetailsDtoTemp orderDetailsDtoTemp : orderDetailsDtoTempList) {
                                if (orderDetailsDtoTemp.getProductId().equals(productId)) {
                                    ApiWarehouseLOutDetailsDtoTemp temp = new ApiWarehouseLOutDetailsDtoTemp();
                                    SerBrandSeriesProductInfo productInfo = productInfoService.getById(productId);
                                    temp.setProductName(productInfo.getProductName());
                                    //扫描到的多余箱数
                                    int boxCountTemp = scanDetailsDtoTemp.getBoxNumber() + entry.getValue().size() - orderDetailsDtoTemp.getBoxNumber();
                                    if (boxCountTemp > 0) {
                                        temp.setBoxCount(boxCountTemp);
                                        LInDetailsDtoTempList.add(temp);
                                    }
                                }
                            }
                        }
                    }
                }

                if (LInDetailsDtoTempList.size() > 0) {
                    PutOutDto dto = new PutOutDto();
                    dto.setFlag(false);
                    dto.setMessage("扫码箱数大于订单箱数");
                    dto.setObj(saveListTemp);
                    map.put("putOutFlag", dto);
                    return map;
                }else{
                    boolean flag;
                    //订单箱数
                    int orderBoxCountTemp = orderBoxCount;
                    //已收到箱数
                    int inBoxCountTemp = getOrderInBoxCount(command.getPurchaseId());
                    //扫描到的箱数+已收货箱数
                    int allBoxCountTemp = inBoxCountTemp+scanBoxCount;
                    if(orderBoxCountTemp<inBoxCountTemp){
                        flag = updateBatchById(saveListTemp);
                        if(flag){
                            //更改订单状态
                            purchaseOrderInfo.setOrderState(3);
                            purchaseOrderInfo.setUpdateTime(new Date());
                            purchaseOrderInfoService.updateById(purchaseOrderInfo);
                        }
                        PutOutDto dto = new PutOutDto();
                        dto.setFlag(true);
                        dto.setMessage("收货成功");
                        dto.setObj("订单未完成");
                        map.put("putOutFlag", dto);
                        return map;
                    }else if(allBoxCountTemp == orderBoxCountTemp){//订单已完成
                        flag = updateBatchById(saveListTemp);
                        if(flag){
                            //更改订单状态
                            purchaseOrderInfo.setOrderState(4);
                            purchaseOrderInfo.setUpdateTime(new Date());
                            purchaseOrderInfoService.updateById(purchaseOrderInfo);
                        }
                        PutOutDto dto = new PutOutDto();
                        dto.setFlag(true);
                        dto.setMessage("收货成功");
                        dto.setObj("订单已完成");
                        map.put("putOutFlag", dto);
                        return map;
                    }else{
                        flag = updateBatchById(saveListTemp);
                        if(flag){
                            //更改订单状态
                            purchaseOrderInfo.setOrderState(3);
                            purchaseOrderInfo.setUpdateTime(new Date());
                            purchaseOrderInfoService.updateById(purchaseOrderInfo);
                        }
                        PutOutDto dto = new PutOutDto();
                        dto.setFlag(true);
                        dto.setMessage("收货成功");
                        dto.setObj("订单未完成");
                        map.put("putOutFlag", dto);
                        return map;
                    }
                }
            }else{
                boolean flag;
                //订单箱数
                int orderBoxCountTemp = orderBoxCount;
                //已收到箱数
                int inBoxCountTemp = getOrderInBoxCount(command.getPurchaseId());
                //扫描到的箱数+已收货箱数
                int allBoxCountTemp = inBoxCountTemp+scanBoxCount;
                if(orderBoxCountTemp<inBoxCountTemp){
                    flag = updateBatchById(saveListTemp);
                    if(flag){
                        //更改订单状态
                        purchaseOrderInfo.setOrderState(3);
                        purchaseOrderInfo.setUpdateTime(new Date());
                        purchaseOrderInfoService.updateById(purchaseOrderInfo);
                    }
                    PutOutDto dto = new PutOutDto();
                    dto.setFlag(true);
                    dto.setMessage("收货成功");
                    dto.setObj("订单未完成");
                    map.put("putOutFlag", dto);
                    return map;
                }else if(allBoxCountTemp == orderBoxCountTemp){//订单已完成
                    flag = updateBatchById(saveListTemp);
                    if(flag){
                        //更改订单状态
                        purchaseOrderInfo.setOrderState(4);
                        purchaseOrderInfo.setUpdateTime(new Date());
                        purchaseOrderInfoService.updateById(purchaseOrderInfo);
                    }
                    PutOutDto dto = new PutOutDto();
                    dto.setFlag(true);
                    dto.setMessage("收货成功");
                    dto.setObj("订单已完成");
                    map.put("putOutFlag", dto);
                    return map;
                }else{
                    flag = updateBatchById(saveListTemp);
                    if(flag){
                        //更改订单状态
                        purchaseOrderInfo.setOrderState(3);
                        purchaseOrderInfo.setUpdateTime(new Date());
                        purchaseOrderInfoService.updateById(purchaseOrderInfo);
                    }
                    PutOutDto dto = new PutOutDto();
                    dto.setFlag(true);
                    dto.setMessage("收货成功");
                    dto.setObj("订单未完成");
                    map.put("putOutFlag", dto);
                    return map;
                }
            }
        }
    }

    @Override
    public int getOrderInBoxCount(Long purchaseId) {
        return baseMapper.getOrderInBoxCount(purchaseId);
    }

    @Override
    public List<GoodsPutInVo> findGoodsPutInVoByCondition(QueryGoodsPutCondition condition) {
        return baseMapper.selectGoodsPutInVoByCondition(condition);
    }
}
