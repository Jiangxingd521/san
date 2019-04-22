package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.PrizeSetLogCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.GoodsPutOutVo;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetLogVo;
import com.ningyang.os.action.output.vo.web.serve.PrizeTicketLogVo;
import com.ningyang.os.dao.SerPrizeRecodeInfoMapper;
import com.ningyang.os.pojo.SerPrizeRecodeInfo;
import com.ningyang.os.pojo.SerPrizeSetInfo;
import com.ningyang.os.service.ILSerWarehouseGoodsOutInfoService;
import com.ningyang.os.service.ISerPrizeRecodeInfoService;
import com.ningyang.os.service.ISerPrizeSetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.ningyang.os.action.utils.DateUtil.timeToStr;
import static com.ningyang.os.action.utils.RandomUtil.randomArray;

/**
 * <p>
 * 布奖记录 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@Service
public class SerPrizeRecodeInfoServiceImpl extends ServiceImpl<SerPrizeRecodeInfoMapper, SerPrizeRecodeInfo> implements ISerPrizeRecodeInfoService {

    @Autowired
    private ISerPrizeSetInfoService infoService;
    @Autowired
    private ILSerWarehouseGoodsOutInfoService outInfoService;


    @Override
    public Page<PrizeSetLogVo> findPrizeSetLogVoPageByCondition(QueryPrizeCondition condition) {
        Page<PrizeSetLogVo> pageVo = new Page<>();
        List<PrizeSetLogVo> listVoTemp = baseMapper.selectPrizeSetLogVoPageByCondition(condition);
        long total = baseMapper.selectPrizeSetLogVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }


    @Override
    public boolean add(PrizeSetLogCommand command, Long userId) {
        List<SerPrizeRecodeInfo> listData = new ArrayList<>();
        List<GoodsPutOutVo> goodsPutOutVoList;
        //奖项设定内容
        SerPrizeSetInfo info = infoService.getById(command.getPrizeSetId());
        //布奖数量
        int prizeSetNumber = info.getPrizeQuantity();
        //选中的商品
        List<GoodsPutOutVo> listData1 = new ArrayList<>();
        //未选中的商品
        List<GoodsPutOutVo> listData2;
        //1：订单，2：产品系列
        if (command.getPrizeSpecies() == 1) {
            //布奖记录
            //获取订单商品
            goodsPutOutVoList = getGoodsList(1, command.getOrderNo());
            //限制数量
            if (info.getPrizeSetType() == 1) {
                //获取随机不重复数
                int[] count = randomArray(0, goodsPutOutVoList.size() - 1, prizeSetNumber);
                //依据总数随机选出来的商品
                for (int i = 0; i < count.length; i++) {
                    int j = count[i];
                    listData1.add(goodsPutOutVoList.get(j));
                }
                //剩余的商品
                for (int i = 0; i < goodsPutOutVoList.size(); i++) {
                    for (int j = 0; j < listData1.size(); j++) {
                        GoodsPutOutVo vo1 = goodsPutOutVoList.get(i);
                        if (vo1.getGoodsId() == listData1.get(j).getGoodsId()) {
                            goodsPutOutVoList.remove(vo1);
                        }
                    }
                }
                listData2 = goodsPutOutVoList;

                for (GoodsPutOutVo vo : listData1) {
                    SerPrizeRecodeInfo recodeInfo = new SerPrizeRecodeInfo();
                    recodeInfo.setOrderNo(vo.getOrderNo());
                    recodeInfo.setProductCode(vo.getProductCode());
                    recodeInfo.setPrizeSetId(info.getPrizeSetId());
                    recodeInfo.setPrizeSetName(info.getPrizeSetName());
                    recodeInfo.setProdId(vo.getProductId());
                    recodeInfo.setMemberType(info.getMemberType());
                    recodeInfo.setRegionId(info.getRegionId());
                    //奖项类型
                    String setTypeStr = info.getSdata1();
                    recodeInfo.setSdata1(setTypeStr);
                    if (setTypeStr.equalsIgnoreCase("HB")) {//红包
                        BigDecimal randomMoney = getRandomMoney(info.getMoney(), info.getMoneyEnd());
                        recodeInfo.setMoney(randomMoney);
                    } else if (setTypeStr.equalsIgnoreCase("TP")) {//积分
                        int point = getRandomPoint(info.getPonit(), info.getPointEnd());
                        recodeInfo.setPonit(point);
                    }
                    recodeInfo.setPrizeSetType(info.getPrizeSetType());
                    recodeInfo.setPrizeModeType(info.getPrizeModeType());
                    recodeInfo.setPrizeState(1);
                    recodeInfo.setPrizeStartDate(info.getPrizeStartDate());
                    recodeInfo.setPrizeEndDate(info.getPrizeEndDate());
                    recodeInfo.setUserId(userId);
                    recodeInfo.setCreateTime(new Date());
                    recodeInfo.setUpdateTime(new Date());
                    listData.add(recodeInfo);
                }

                for (GoodsPutOutVo vo : listData2) {
                    SerPrizeRecodeInfo recodeInfo = new SerPrizeRecodeInfo();
                    recodeInfo.setOrderNo(vo.getOrderNo());
                    recodeInfo.setProductCode(vo.getProductCode());
                    recodeInfo.setPrizeSetId(info.getPrizeSetId());
                    recodeInfo.setPrizeSetName(info.getPrizeSetName());
                    recodeInfo.setProdId(vo.getProductId());
                    recodeInfo.setMemberType(info.getMemberType());
                    recodeInfo.setRegionId(info.getRegionId());
                    //奖项类型
                    String setTypeStr = info.getSdata1();
                    recodeInfo.setSdata1(setTypeStr);
                    if (setTypeStr.equalsIgnoreCase("HB")) {//红包
                        recodeInfo.setMoney(new BigDecimal(0));
                    } else if (setTypeStr.equalsIgnoreCase("TP")) {//积分
                        recodeInfo.setPonit(0);
                    }
                    recodeInfo.setPrizeSetType(info.getPrizeSetType());
                    recodeInfo.setPrizeModeType(info.getPrizeModeType());
                    recodeInfo.setPrizeState(1);
                    recodeInfo.setPrizeStartDate(info.getPrizeStartDate());
                    recodeInfo.setPrizeEndDate(info.getPrizeEndDate());
                    recodeInfo.setUserId(userId);
                    recodeInfo.setCreateTime(new Date());
                    recodeInfo.setUpdateTime(new Date());
                    listData.add(recodeInfo);
                }
            } else {//不限量
                listData1 = goodsPutOutVoList;
                for (GoodsPutOutVo vo : listData1) {
                    SerPrizeRecodeInfo recodeInfo = new SerPrizeRecodeInfo();
                    recodeInfo.setOrderNo(vo.getOrderNo());
                    recodeInfo.setProductCode(vo.getProductCode());
                    recodeInfo.setPrizeSetId(info.getPrizeSetId());
                    recodeInfo.setPrizeSetName(info.getPrizeSetName());
                    recodeInfo.setProdId(vo.getProductId());
                    recodeInfo.setMemberType(info.getMemberType());
                    recodeInfo.setRegionId(info.getRegionId());
                    //奖项类型
                    String setTypeStr = info.getSdata1();
                    recodeInfo.setSdata1(setTypeStr);
                    if (setTypeStr.equalsIgnoreCase("HB")) {//红包
                        recodeInfo.setMoney(info.getMoney());
                    } else if (setTypeStr.equalsIgnoreCase("TP")) {//积分
                        recodeInfo.setPonit(info.getPonit());
                    }
                    recodeInfo.setPrizeSetType(info.getPrizeSetType());
                    recodeInfo.setPrizeModeType(info.getPrizeModeType());
                    recodeInfo.setPrizeState(1);
                    recodeInfo.setPrizeStartDate(info.getPrizeStartDate());
                    recodeInfo.setPrizeEndDate(info.getPrizeEndDate());
                    recodeInfo.setUserId(userId);
                    recodeInfo.setCreateTime(new Date());
                    recodeInfo.setUpdateTime(new Date());
                    listData.add(recodeInfo);
                }
            }
        } else {
            //产品系列
            goodsPutOutVoList = getGoodsList(2, String.valueOf(command.getProdId()));
            //限制数量
            if (info.getPrizeSetType() == 1) {
                //获取随机不重复数
                int[] count = randomArray(0, goodsPutOutVoList.size() - 1, prizeSetNumber);
                //依据总数随机选出来的商品
                for (int i = 0; i < count.length; i++) {
                    int j = count[i];
                    listData1.add(goodsPutOutVoList.get(j));
                }
                //剩余的商品
                for (int i = 0; i < goodsPutOutVoList.size(); i++) {
                    for (int j = 0; j < listData1.size(); j++) {
                        GoodsPutOutVo vo1 = goodsPutOutVoList.get(i);
                        if (vo1.getGoodsId() == listData1.get(j).getGoodsId()) {
                            goodsPutOutVoList.remove(vo1);
                        }
                    }
                }
                listData2 = goodsPutOutVoList;

                for (GoodsPutOutVo vo : listData1) {
                    SerPrizeRecodeInfo recodeInfo = new SerPrizeRecodeInfo();
                    recodeInfo.setOrderNo(vo.getOrderNo());
                    recodeInfo.setProductCode(vo.getProductCode());
                    recodeInfo.setPrizeSetId(info.getPrizeSetId());
                    recodeInfo.setPrizeSetName(info.getPrizeSetName());
                    recodeInfo.setProdId(vo.getProductId());
                    recodeInfo.setMemberType(info.getMemberType());
                    recodeInfo.setRegionId(info.getRegionId());
                    //奖项类型
                    String setTypeStr = info.getSdata1();
                    recodeInfo.setSdata1(setTypeStr);
                    if (setTypeStr.equalsIgnoreCase("HB")) {//红包
                        BigDecimal randomMoney = getRandomMoney(info.getMoney(), info.getMoneyEnd());
                        recodeInfo.setMoney(randomMoney);
                    } else if (setTypeStr.equalsIgnoreCase("TP")) {//积分
                        int point = getRandomPoint(info.getPonit(), info.getPointEnd());
                        recodeInfo.setPonit(point);
                    }
                    recodeInfo.setPrizeSetType(info.getPrizeSetType());
                    recodeInfo.setPrizeModeType(info.getPrizeModeType());
                    recodeInfo.setPrizeState(1);
                    recodeInfo.setPrizeStartDate(info.getPrizeStartDate());
                    recodeInfo.setPrizeEndDate(info.getPrizeEndDate());
                    recodeInfo.setUserId(userId);
                    recodeInfo.setCreateTime(new Date());
                    recodeInfo.setUpdateTime(new Date());
                    listData.add(recodeInfo);
                }

                for (GoodsPutOutVo vo : listData2) {
                    SerPrizeRecodeInfo recodeInfo = new SerPrizeRecodeInfo();
                    recodeInfo.setOrderNo(vo.getOrderNo());
                    recodeInfo.setProductCode(vo.getProductCode());
                    recodeInfo.setPrizeSetId(info.getPrizeSetId());
                    recodeInfo.setPrizeSetName(info.getPrizeSetName());
                    recodeInfo.setProdId(vo.getProductId());
                    recodeInfo.setMemberType(info.getMemberType());
                    recodeInfo.setRegionId(info.getRegionId());
                    //奖项类型
                    String setTypeStr = info.getSdata1();
                    recodeInfo.setSdata1(setTypeStr);
                    if (setTypeStr.equalsIgnoreCase("HB")) {//红包
                        recodeInfo.setMoney(new BigDecimal(0));
                    } else if (setTypeStr.equalsIgnoreCase("TP")) {//积分
                        recodeInfo.setPonit(0);
                    }
                    recodeInfo.setPrizeSetType(info.getPrizeSetType());
                    recodeInfo.setPrizeModeType(info.getPrizeModeType());
                    recodeInfo.setPrizeState(1);
                    recodeInfo.setPrizeStartDate(info.getPrizeStartDate());
                    recodeInfo.setPrizeEndDate(info.getPrizeEndDate());
                    recodeInfo.setUserId(userId);
                    recodeInfo.setCreateTime(new Date());
                    recodeInfo.setUpdateTime(new Date());
                    listData.add(recodeInfo);
                }
            } else {//不限量
                listData1 = goodsPutOutVoList;
                for (GoodsPutOutVo vo : listData1) {
                    SerPrizeRecodeInfo recodeInfo = new SerPrizeRecodeInfo();
                    recodeInfo.setOrderNo(vo.getOrderNo());
                    recodeInfo.setProductCode(vo.getProductCode());
                    recodeInfo.setPrizeSetId(info.getPrizeSetId());
                    recodeInfo.setPrizeSetName(info.getPrizeSetName());
                    recodeInfo.setProdId(vo.getProductId());
                    recodeInfo.setMemberType(info.getMemberType());
                    recodeInfo.setRegionId(info.getRegionId());
                    //奖项类型
                    String setTypeStr = info.getSdata1();
                    recodeInfo.setSdata1(setTypeStr);
                    if (setTypeStr.equalsIgnoreCase("HB")) {//红包
                        recodeInfo.setMoney(info.getMoney());
                    } else if (setTypeStr.equalsIgnoreCase("TP")) {//积分
                        recodeInfo.setPonit(info.getPonit());
                    }
                    recodeInfo.setPrizeSetType(info.getPrizeSetType());
                    recodeInfo.setPrizeModeType(info.getPrizeModeType());
                    recodeInfo.setPrizeState(1);
                    recodeInfo.setPrizeStartDate(info.getPrizeStartDate());
                    recodeInfo.setPrizeEndDate(info.getPrizeEndDate());
                    recodeInfo.setUserId(userId);
                    recodeInfo.setCreateTime(new Date());
                    recodeInfo.setUpdateTime(new Date());
                    listData.add(recodeInfo);
                }
            }
        }
        return baseMapper.insertBatch(listData);
    }


    @Override
    public Page<PrizeTicketLogVo> findPrizeTicketLogVoPageByCondition(QueryPrizeCondition condition) {
        Page<PrizeTicketLogVo> pageVo = new Page<>();
        List<PrizeTicketLogVo> listVoTemp = baseMapper.selectPrizeTicketLogVoPageByCondition(condition);
        for (PrizeTicketLogVo vo : listVoTemp) {
            vo.setTicketTimeStr(timeToStr(vo.getTicketTime()));
        }
        long total = baseMapper.selectPrizeTicketLogVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean addMake(PrizeSetLogCommand command, Long userId) {
        return false;
    }

    /**
     * 获取订单商品
     *
     * @param type      1: 通过订单查询,2:通过产品系列查询
     * @param typeValue
     * @return
     */
    private List<GoodsPutOutVo> getGoodsList(int type, String typeValue) {
        QueryGoodsPutCondition condition = new QueryGoodsPutCondition();
        if (type == 1) {
            condition.setOrderNo(typeValue);
        } else {
            condition.setProductId(Long.valueOf(typeValue));
        }
        return outInfoService.findGoodsPutOutVoByCondition(condition);
    }

    /**
     * 获取某个范围内随机金额
     *
     * @param minVal
     * @param maxVal
     * @return
     */
    private BigDecimal getRandomMoney(BigDecimal minVal, BigDecimal maxVal) {
        BigDecimal maxValTemp = maxVal.multiply(new BigDecimal(100));
        BigDecimal minValTemp = minVal.multiply(new BigDecimal(100));
        int max = maxValTemp.intValue();
        int min = minValTemp.intValue();
        Random random = new Random();
        int randomMoneyValTemp = random.nextInt(max) % (max - min + 1) + min;
        BigDecimal randomMoneyVal = new BigDecimal(randomMoneyValTemp).divide(new BigDecimal(100));
        return randomMoneyVal;
    }

    /**
     * 获取某个范围内随机积分
     *
     * @param minVal
     * @param maxVal
     * @return
     */
    private int getRandomPoint(int minVal, int maxVal) {
        int max = maxVal;
        int min = minVal;
        Random random = new Random();
        int randomMoneyVal = random.nextInt(max) % (max - min + 1) + min;
        return randomMoneyVal;
    }

}
