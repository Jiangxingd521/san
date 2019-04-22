package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.PrizeSetCommand;
import com.ningyang.os.action.input.condition.serve.QueryPrizeCondition;
import com.ningyang.os.action.output.vo.web.serve.PrizeManagerVo;
import com.ningyang.os.action.output.vo.web.serve.PrizeSetVo;
import com.ningyang.os.dao.SerPrizeSetInfoMapper;
import com.ningyang.os.pojo.SerPrizeManagerInfo;
import com.ningyang.os.pojo.SerPrizeSetInfo;
import com.ningyang.os.service.ISerPrizeManagerInfoService;
import com.ningyang.os.service.ISerPrizeSetInfoService;
import com.ningyang.os.service.ISysBaseRegionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.dateToDate;

/**
 * <p>
 * 奖项设定 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@Service
public class SerPrizeSetInfoServiceImpl extends ServiceImpl<SerPrizeSetInfoMapper, SerPrizeSetInfo> implements ISerPrizeSetInfoService {

    @Autowired
    private ISysBaseRegionInfoService regionInfoService;
    @Autowired
    private ISerPrizeManagerInfoService managerInfoService;

    @Override
    public Page<PrizeSetVo> findPrizeSetVoListPageByCondition(QueryPrizeCondition condition) {
        Page<PrizeSetVo> pageVo = new Page<>();
        List<PrizeSetVo> listTemp = baseMapper.selectPrizeSetVoListPageByCondition(condition);
        for (PrizeSetVo vo : listTemp) {
            //金额
            BigDecimal money = new BigDecimal(vo.getMoney()!=null?vo.getMoney():"0").divide(new BigDecimal(100));
            BigDecimal moneyEnd = new BigDecimal(vo.getMoneyEnd()!=null?vo.getMoneyEnd():"0").divide(new BigDecimal(100));
            vo.setMoney(money.toString());
            vo.setMoneyEnd(moneyEnd.toString());
            //奖项类型
            PrizeManagerVo managerVo = managerInfoService.findPrizeManagerVoById(vo.getManagerId());
            vo.setPrizeManager(managerVo);
            //开始结束布奖日期
            Date[] prizeDate = {vo.getPrizeStartDate(), vo.getPrizeEndDate()};
            vo.setPrizeDate(prizeDate);
            vo.setPrizeStartDateStr(dateToDate(vo.getPrizeStartDate()));
            vo.setPrizeEndDateStr(dateToDate(vo.getPrizeEndDate()));
            if (vo.getRegionId() != null) {
                List<String> regionList = regionInfoService.findRegionThreeList(String.valueOf(vo.getRegionId()));
                vo.setRegionList(regionList);
            }
        }
        long total = baseMapper.selectPrizeSetVoListPageCountByCondition(condition);
        pageVo.setRecords(listTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public List<PrizeSetVo> findPrizeSetVoListByCondition(QueryPrizeCondition condition) {
        List<PrizeSetVo> listTemp = baseMapper.selectPrizeSetVoListByCondition(condition);
        for (PrizeSetVo vo : listTemp) {
            BigDecimal money = new BigDecimal(vo.getMoney()).divide(new BigDecimal(100));
            BigDecimal moneyEnd = new BigDecimal(vo.getMoneyEnd()).divide(new BigDecimal(100));
            vo.setMoney(money.toString());
            vo.setMoneyEnd(moneyEnd.toString());
            //奖项类型
            PrizeManagerVo managerVo = managerInfoService.findPrizeManagerVoById(vo.getManagerId());
            vo.setPrizeManager(managerVo);

            Date[] prizeDate = {vo.getPrizeStartDate(), vo.getPrizeEndDate()};
            vo.setPrizeDate(prizeDate);
            vo.setPrizeStartDateStr(dateToDate(vo.getPrizeStartDate()));
            vo.setPrizeEndDateStr(dateToDate(vo.getPrizeEndDate()));
            if (vo.getRegionId() != null) {
                List<String> regionList = regionInfoService.findRegionThreeList(String.valueOf(vo.getRegionId()));
                vo.setRegionList(regionList);
            }
        }
        return listTemp;
    }

    @Override
    public boolean addOrUpdate(PrizeSetCommand command, Long userId) {
        SerPrizeSetInfo info = getById(command.getPrizeSetId());
        boolean flag;
        if (info != null) {
            info.setPrizeManagerId(command.getManagerId());
            info.setPrizeSetName(command.getPrizeSetName());
            info.setProdId(command.getProdId());
            info.setMemberType(command.getMemberType());
            info.setRegionId(command.getRegionId());
            info.setPrizeQuantity(command.getPrizeQuantity());
            info.setMoney(command.getMoney().multiply(new BigDecimal(100)));
            info.setMoneyEnd(command.getMoneyEnd().multiply(new BigDecimal(100)));
            info.setPonit(command.getPonit());
            info.setPointEnd(command.getPointEnd());
            info.setPrizeSetType(command.getPrizeSetType());
            info.setPrizeModeType(command.getPrizeModeType());
            info.setCardMoney(command.getCardMoney());
            info.setCardCouponMoney(command.getCardCouponMoney());
            info.setPrizeStartDate(command.getPrizeDate()[0]);
            info.setPrizeEndDate(command.getPrizeDate()[1]);
            info.setIdata1(command.getSetState());
            SerPrizeManagerInfo managerInfo = managerInfoService.getById(command.getManagerId());
            info.setSdata1(managerInfo.getSdata1());
            info.setUserId(userId);
            info.setUpdateTime(new Date());
            flag = updateById(info);
        } else {
            info = new SerPrizeSetInfo();
            info.setPrizeManagerId(command.getManagerId());
            info.setPrizeSetName(command.getPrizeSetName());
            info.setProdId(command.getProdId());
            info.setMemberType(command.getMemberType());
            info.setRegionId(command.getRegionId());
            info.setPrizeQuantity(command.getPrizeQuantity());
            info.setMoney(command.getMoney().multiply(new BigDecimal(100)));
            info.setMoneyEnd(command.getMoneyEnd().multiply(new BigDecimal(100)));
            info.setPonit(command.getPonit());
            info.setPointEnd(command.getPointEnd());
            info.setPrizeSetType(command.getPrizeSetType());
            info.setPrizeModeType(command.getPrizeModeType());
            info.setCardMoney(command.getCardMoney());
            info.setCardCouponMoney(command.getCardCouponMoney());
            info.setPrizeStartDate(command.getPrizeDate()[0]);
            info.setPrizeEndDate(command.getPrizeDate()[1]);
            info.setIdata1(0);
            SerPrizeManagerInfo managerInfo = managerInfoService.getById(command.getManagerId());
            info.setSdata1(managerInfo.getSdata1());
            info.setUserId(userId);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag = save(info);
        }
        return flag;
    }

    @Override
    public PrizeSetVo findPrizeSetVoById(Long prizeSetId) {
        PrizeSetVo vo = baseMapper.selectPrizeSetVoById(prizeSetId);
        BigDecimal money = new BigDecimal(vo.getMoney()).divide(new BigDecimal(100));
        BigDecimal moneyEnd = new BigDecimal(vo.getMoneyEnd()).divide(new BigDecimal(100));

        vo.setMoney(money.toString());
        vo.setMoneyEnd(moneyEnd.toString());

        //奖项类型
        PrizeManagerVo managerVo = managerInfoService.findPrizeManagerVoById(vo.getManagerId());
        vo.setPrizeManager(managerVo);

        Date[] prizeDate = {vo.getPrizeStartDate(), vo.getPrizeEndDate()};
        vo.setPrizeDate(prizeDate);
        vo.setPrizeStartDateStr(dateToDate(vo.getPrizeStartDate()));
        vo.setPrizeEndDateStr(dateToDate(vo.getPrizeEndDate()));
        if (vo.getRegionId() != null) {
            List<String> regionList = regionInfoService.findRegionThreeList(String.valueOf(vo.getRegionId()));
            vo.setRegionList(regionList);
        }
        return vo;
    }
}
