package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.api.ApiWarehousePutOutCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.output.dto.serve.ApiWarehouseLOutDetailsDtoTemp;
import com.ningyang.os.action.output.dto.serve.ApiWarehouseOutDetailsDtoTemp;
import com.ningyang.os.action.output.dto.serve.PutOutDto;
import com.ningyang.os.action.output.vo.web.serve.GoodsPutOutVo;
import com.ningyang.os.dao.LSerWarehouseGoodsOutInfoMapper;
import com.ningyang.os.pojo.*;
import com.ningyang.os.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.ningyang.os.action.utils.DateUtil.timeToStr;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * <p>
 * 商品出库记录日志 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@Service
public class LSerWarehouseGoodsOutInfoServiceImpl extends ServiceImpl<LSerWarehouseGoodsOutInfoMapper, LSerWarehouseGoodsOutInfo> implements ILSerWarehouseGoodsOutInfoService {

    @Autowired
    private ISerOrderInfoService orderInfoService;
    @Autowired
    private ISerOrderInfoDetailsService detailsService;
    @Autowired
    private ISerGoodsInfoService goodsInfoService;
    @Autowired
    private ILSerWarehouseGoodsInfoService putInService;
    @Autowired
    private ISerBrandSeriesProductInfoService productInfoService;

    //商品出库
    @Override
    public Map<String, Object> add(ApiWarehousePutOutCommand command) {
        Map<String, Object> map = new HashMap<>();
        //订单信息
        SerOrderInfo orderInfo = orderInfoService.getById(command.getOrderId());
        //订单经销商
        Long dealerId = orderInfo.getDealerId();
        //订单箱数
        QueryGoodsPutCondition condition = new QueryGoodsPutCondition();
        condition.setOrderId(command.getOrderId());
        int orderBoxCount = orderInfoService.getOrderBoxCount(condition);
        //扫码箱数
        int scanBoxCount = command.getBoxCode().length;
        //已出箱数
        int outBoxCount = getOrderOutBoxCount(command.getOrderId());
        //实际需要出箱数
        int actualBoxCount = orderBoxCount - outBoxCount;
//        System.out.println("orderBoxCount:" + orderBoxCount + " scanBoxCount:" + scanBoxCount + " outBoxCount:" + outBoxCount + " actualBoxCount:" + actualBoxCount);
        if (scanBoxCount > actualBoxCount) {
            PutOutDto dto = new PutOutDto();
            dto.setFlag(false);
            dto.setMessage("扫码箱数大于订单箱数");
            map.put("putOutFlag", dto);
            return map;
        } else {
            List<LSerWarehouseGoodsInfo> unSafeList = new ArrayList<>();
            //查询所有箱子对应的商品
            List<LSerWarehouseGoodsInfo> goodsInfoList = new ArrayList<>();
            for (String boxNo : command.getBoxCode()) {
                //判断是否入库的商品
                LSerWarehouseGoodsInfo goodsInfo = putInService.getOne(new QueryWrapper<LSerWarehouseGoodsInfo>().eq("box_no", boxNo));
                if (goodsInfo != null) {
                    //判断是否已经出库的商品
                    LSerWarehouseGoodsOutInfo outInfo = getOne(new QueryWrapper<LSerWarehouseGoodsOutInfo>().eq("box_no", boxNo));
                    if(outInfo!=null){
                        LSerWarehouseGoodsInfo infoTemp = new LSerWarehouseGoodsInfo();
                        infoTemp.setBoxNo(boxNo);
                        unSafeList.add(infoTemp);
                        PutOutDto dto = new PutOutDto();
                        dto.setFlag(false);
                        dto.setMessage("此商品已出货");
                        dto.setObj(unSafeList);
                        map.put("putOutFlag", dto);
                        return map;
                    }else{
                        goodsInfoList.add(goodsInfo);
                    }
                } else {
                    LSerWarehouseGoodsInfo infoTemp = new LSerWarehouseGoodsInfo();
                    infoTemp.setBoxNo(boxNo);
                    unSafeList.add(infoTemp);
                }
            }
            //扫描到未入库的商品
            if (unSafeList.size() > 0) {
                PutOutDto dto = new PutOutDto();
                dto.setFlag(false);
                dto.setMessage("存在未入库的商品");
                dto.setObj(unSafeList);
                map.put("putOutFlag", dto);
                return map;
            }
            //查看订单详情
            List<SerOrderInfoDetails> detailsList = detailsService.list(new QueryWrapper<SerOrderInfoDetails>()
                    .eq("order_id", command.getOrderId()));

            //当前订单明细
            Map<Long, List<SerOrderInfoDetails>> groupByDetails = detailsList.stream().collect(Collectors
                    .groupingBy(SerOrderInfoDetails::getProductId));
            //
            Map<Long, List<LSerWarehouseGoodsInfo>> groupByGoods = goodsInfoList.stream().collect(Collectors
                    .groupingBy(LSerWarehouseGoodsInfo::getProductId));

            //订单明细
            List<ApiWarehouseOutDetailsDtoTemp> orderDetailsDtoTempList = new ArrayList<>();
            for (Map.Entry<Long, List<SerOrderInfoDetails>> entry : groupByDetails.entrySet()) {
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
                dto.setMessage("扫码箱数大于订单箱数");
                dto.setObj(unSafeList);
                map.put("putOutFlag", dto);
                return map;
            }

            //查询已经出了的各商品箱数
            List<LSerWarehouseGoodsOutInfo> outDetailsDtoTempList = list(new QueryWrapper<LSerWarehouseGoodsOutInfo>()
                    .eq("order_id", command.getOrderId()));

            if (outDetailsDtoTempList.size() > 0) {
                Map<Long, List<LSerWarehouseGoodsOutInfo>> groupByOutGoods = outDetailsDtoTempList.stream().collect(Collectors
                        .groupingBy(LSerWarehouseGoodsOutInfo::getProductId));

                List<ApiWarehouseLOutDetailsDtoTemp> LOutDetailsDtoTempList = new ArrayList<>();
                for (Map.Entry<Long, List<LSerWarehouseGoodsOutInfo>> entry : groupByOutGoods.entrySet()) {
//                    System.out.println("键 key ："+entry.getKey()+" 值value ："+entry.getValue());
                    //已经发货的系列产品id
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
                                    if(boxCountTemp>0){
                                        temp.setBoxCount(boxCountTemp);
                                        LOutDetailsDtoTempList.add(temp);
                                    }
                                }
                            }
                        }
                    }
                }
                if (LOutDetailsDtoTempList.size() > 0) {
                    PutOutDto dto = new PutOutDto();
                    dto.setFlag(false);
                    dto.setMessage("扫码箱数大于订单箱数");
                    dto.setObj(unSafeList);
                    map.put("putOutFlag", dto);
                    return map;
                }else{
                    List<LSerWarehouseGoodsOutInfo> infoList = new ArrayList<>();
                    for (String boxNo : command.getBoxCode()) {
                        LSerWarehouseGoodsOutInfo info = new LSerWarehouseGoodsOutInfo();
                        info.setOrderId(command.getOrderId());
                        info.setWarehouseId(command.getWarehouseId());
                        info.setBoxNo(boxNo);
                        info.setDealerId(dealerId);
                        SerGoodsInfo goodsInfo = goodsInfoService.getOne(new QueryWrapper<SerGoodsInfo>()
                                .eq("M5",boxNo));
                        info.setProductId(goodsInfo.getBrandSeriesProductId());
                        info.setUserId(command.getUserId());
                        info.setGoodsOutTime(new Date());
                        info.setCreateTime(new Date());
                        info.setUpdateTime(new Date());
                        infoList.add(info);
                    }
                    //去除数组里面的重复对象
                    List<LSerWarehouseGoodsOutInfo> listTemp = infoList.stream().collect(
                            collectingAndThen(toCollection(() -> new TreeSet<>(comparing(LSerWarehouseGoodsOutInfo::getBoxNo))), ArrayList::new)
                    );
                    boolean flag;
                    //订单箱数
//                    int orderBoxCountTemp = orderInfoService.getOrderBoxCount(condition);
                    int orderBoxCountTemp = orderBoxCount;
                    //已出箱数
                    int outBoxCountTemp = getOrderOutBoxCount(command.getOrderId());
                    //扫描到的箱数+已出货箱数
                    int allBoxCountTemp = outBoxCountTemp+scanBoxCount;
                    if(allBoxCountTemp<orderBoxCountTemp){//订单未完成
                        flag = saveBatch(listTemp);
                        if(flag){
                            //更改订单状态
                            orderInfo.setOrderState(3);
                            orderInfo.setUpdateTime(new Date());
                            orderInfoService.updateById(orderInfo);
                        }
                        PutOutDto dto = new PutOutDto();
                        dto.setFlag(true);
                        dto.setMessage("出货成功");
                        dto.setObj("订单未完成");
                        map.put("putOutFlag", dto);
                        return map;
                    }else if(allBoxCountTemp == orderBoxCountTemp){//订单已完成
                        flag = saveBatch(listTemp);
                        if(flag){
                            //更改订单状态
                            orderInfo.setOrderState(4);
                            orderInfo.setUpdateTime(new Date());
                            orderInfoService.updateById(orderInfo);
                        }
                        PutOutDto dto = new PutOutDto();
                        dto.setFlag(true);
                        dto.setMessage("出货成功");
                        dto.setObj("订单已完成");
                        map.put("putOutFlag", dto);
                        return map;
                    }else{
                        flag = saveBatch(listTemp);
                        if(flag){
                            //更改订单状态
                            orderInfo.setOrderState(3);
                            orderInfo.setUpdateTime(new Date());
                            orderInfoService.updateById(orderInfo);
                        }
                        PutOutDto dto = new PutOutDto();
                        dto.setFlag(true);
                        dto.setMessage("出货成功");
                        dto.setObj("订单未完成");
                        map.put("putOutFlag", dto);
                        return map;
                    }
                }
            } else {
                List<LSerWarehouseGoodsOutInfo> infoList = new ArrayList<>();
                for (String boxNo : command.getBoxCode()) {
                    LSerWarehouseGoodsOutInfo info = new LSerWarehouseGoodsOutInfo();
                    info.setOrderId(command.getOrderId());
                    info.setWarehouseId(command.getWarehouseId());
                    info.setBoxNo(boxNo);
                    info.setDealerId(dealerId);
                    SerGoodsInfo goodsInfo = goodsInfoService.getOne(new QueryWrapper<SerGoodsInfo>()
                            .eq("M5",boxNo));
                    info.setProductId(goodsInfo.getBrandSeriesProductId());
                    info.setUserId(command.getUserId());
                    info.setGoodsOutTime(new Date());
                    info.setCreateTime(new Date());
                    info.setUpdateTime(new Date());
                    infoList.add(info);
                }
                //去除数组里面的重复对象
                List<LSerWarehouseGoodsOutInfo> listTemp = infoList.stream().collect(
                        collectingAndThen(toCollection(() -> new TreeSet<>(comparing(LSerWarehouseGoodsOutInfo::getBoxNo))), ArrayList::new)
                );
                boolean flag;
                //订单箱数
//                int orderBoxCountTemp = orderInfoService.getOrderBoxCount(condition);
                int orderBoxCountTemp = orderBoxCount;
                //已出箱数
                int outBoxCountTemp = getOrderOutBoxCount(command.getOrderId());

                if(orderBoxCountTemp<outBoxCountTemp){//订单未完成
                    flag = saveBatch(listTemp);
                    if(flag){
                        //更改订单状态
                        orderInfo.setOrderState(3);
                        orderInfo.setUpdateTime(new Date());
                        orderInfoService.updateById(orderInfo);
                    }
                    PutOutDto dto = new PutOutDto();
                    dto.setFlag(true);
                    dto.setMessage("出货成功");
                    dto.setObj("订单未完成");
                    map.put("putOutFlag", dto);
                    return map;
                }else if(orderBoxCountTemp == outBoxCountTemp){//订单已完成
                    flag = saveBatch(listTemp);
                    if(flag){
                        //更改订单状态
                        orderInfo.setOrderState(4);
                        orderInfo.setUpdateTime(new Date());
                        orderInfoService.updateById(orderInfo);
                    }
                    PutOutDto dto = new PutOutDto();
                    dto.setFlag(true);
                    dto.setMessage("出货成功");
                    dto.setObj("订单已完成");
                    map.put("putOutFlag", dto);
                    return map;
                }else if(orderBoxCountTemp == scanBoxCount){//订单箱子==扫描到的箱子时
                    flag = saveBatch(listTemp);
                    if(flag){
                        //更改订单状态
                        orderInfo.setOrderState(4);
                        orderInfo.setUpdateTime(new Date());
                        orderInfoService.updateById(orderInfo);
                    }
                    PutOutDto dto = new PutOutDto();
                    dto.setFlag(true);
                    dto.setMessage("出货成功");
                    dto.setObj("订单已完成");
                    map.put("putOutFlag", dto);
                    return map;
                }else{
                    flag = saveBatch(listTemp);
                    if(flag){
                        //更改订单状态
                        orderInfo.setOrderState(3);
                        orderInfo.setUpdateTime(new Date());
                        orderInfoService.updateById(orderInfo);
                    }
                    PutOutDto dto = new PutOutDto();
                    dto.setFlag(true);
                    dto.setMessage("出货成功");
                    dto.setObj("订单未完成");
                    map.put("putOutFlag", dto);
                    return map;
                }
            }
        }
    }

    @Override
    public Page<GoodsPutOutVo> findGoodsPutOutVoPageByCondition(QueryGoodsPutCondition condition) {
        Page<GoodsPutOutVo> pageVo = new Page<>();
        List<GoodsPutOutVo> listVoTemp = baseMapper.selectGoodsPutOutVoPageByCondition(condition);
        for (GoodsPutOutVo vo : listVoTemp) {
            vo.setGoodsOutTimeStr(timeToStr(vo.getGoodsOutTime()));
            QueryGoodsPutCondition putCondition = new QueryGoodsPutCondition();
            putCondition.setOrderId(vo.getOrderId());
            putCondition.setProductId(vo.getProductId());
            int orderBoxCount = orderInfoService.getOrderBoxCount(putCondition);

            vo.setOrderBoxCount(orderBoxCount);
        }
        long total = baseMapper.selectGoodsPutOutVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public List<GoodsPutOutVo> findGoodsPutOutVoByCondition(QueryGoodsPutCondition condition) {
        return baseMapper.selectGoodsPutOutVoByCondition(condition);
    }

    @Override
    public int getWarehouseBoxCount(Long warehouseId) {
        return baseMapper.getWarehouseBoxCount(warehouseId);
    }

    @Override
    public int getOrderOutBoxCount(Long orderId) {
        return baseMapper.getOrderOutBoxCount(orderId);
    }

    @Override
    public List<GoodsPutOutVo> findWarehouseGoodsPutOutVoByCondition(QueryGoodsPutCondition condition) {
        List<GoodsPutOutVo> listVoTemp = baseMapper.selectWarehouseGoodsPutOutVoPageByCondition(condition);
        for (GoodsPutOutVo vo : listVoTemp) {
            vo.setGoodsOutTimeStr(timeToStr(vo.getGoodsOutTime()));
            QueryGoodsPutCondition putCondition = new QueryGoodsPutCondition();
            putCondition.setOrderId(vo.getOrderId());
            putCondition.setProductId(vo.getProductId());
            int orderBoxCount = orderInfoService.getOrderBoxCount(putCondition);
            vo.setOrderBoxCount(orderBoxCount);
        }
        return listVoTemp;
    }

}
