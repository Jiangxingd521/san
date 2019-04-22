package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.DealerCommand;
import com.ningyang.os.action.input.condition.serve.QueryDealerCondition;
import com.ningyang.os.action.output.vo.web.serve.DealerVo;
import com.ningyang.os.dao.SerDealerInfoMapper;
import com.ningyang.os.pojo.SerDealerInfo;
import com.ningyang.os.pojo.SerDealerRegionInfo;
import com.ningyang.os.service.ISerDealerInfoService;
import com.ningyang.os.service.ISerDealerRegionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 供应商信息 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@Service
public class SerDealerInfoServiceImpl extends ServiceImpl<SerDealerInfoMapper, SerDealerInfo> implements ISerDealerInfoService {

    @Autowired
    private ISerDealerRegionInfoService dealerRegionInfoService;

    @Override
    public Page<DealerVo> findDealerVoPageByCondition(QueryDealerCondition condition) {
        Page<DealerVo> pageVo = new Page<>();
        List<DealerVo> listVoTemp = baseMapper.selectDealerVoPageByCondition(condition);
        long total = baseMapper.selectDealerVoPageCountByCondition(condition);
        for (DealerVo vo : listVoTemp) {
            List<String> regionList = dealerRegionInfoService.getDealerRegionList(vo.getDealerId());
            vo.setRegionList(regionList);
            String regionName = dealerRegionInfoService.getDealerRegionName(vo.getDealerId());
            vo.setRegionName(regionName);
        }
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean addOrUpdate(DealerCommand command) {
        SerDealerInfo info = getOne(new QueryWrapper<SerDealerInfo>().eq("id", command.getDealerId()));
        boolean flag1;
        if (info != null) {
            info.setDealerName(command.getDealerName());
            info.setPersonName(command.getPersonName());
            info.setPersonMobile(command.getPersonMobile());
            info.setSocialCode(command.getSocialCode());
            info.setDealerAddress(command.getAddress());
            info.setDealerRemark(command.getDealerRemark());
            info.setDealerState(command.getDealerState());
            info.setIdata1(command.getTypeState());
            info.setIdata2(command.getDealerType());
            info.setUpdateTime(new Date());
            flag1 = updateById(info);
        } else {
            info = new SerDealerInfo();
            info.setDealerName(command.getDealerName());
            info.setPersonName(command.getPersonName());
            info.setPersonMobile(command.getPersonMobile());
            info.setSocialCode(command.getSocialCode());
            info.setDealerAddress(command.getAddress());
            info.setDealerRemark(command.getDealerRemark());
            info.setDealerState(0);
            info.setIdata1(0);
            info.setIdata2(0);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag1 = save(info);
        }

        dealerRegionInfoService.remove(new QueryWrapper<SerDealerRegionInfo>().eq("dealer_id", info.getId()));
        List<SerDealerRegionInfo> dealerRegionInfoList = new ArrayList<>();
        for (Long regionId : command.getRegionList()) {
            SerDealerRegionInfo regionInfo = new SerDealerRegionInfo();
            regionInfo.setDealerId(info.getId());
            regionInfo.setRegionId(regionId);
            dealerRegionInfoList.add(regionInfo);
        }
        boolean flag2 = dealerRegionInfoService.saveBatch(dealerRegionInfoList);
        return flag1 && flag2;
    }

    @Override
    public List<DealerVo> findDealerVoListByCondition() {
        return baseMapper.selectDealerVoListByCondition();
    }

    @Override
    public boolean checkDealerCode(DealerCommand command) {
        int dealerCodeCount = count(new QueryWrapper<SerDealerInfo>().eq("social_code", command.getSocialCode()).eq("idata2",0));
        return dealerCodeCount > 0 ? false : true;
    }

    @Override
    public boolean isOrderInformation(Long dealerId) {

        return baseMapper.isOrderInformation(dealerId)>0;
    }

    @Override
    public boolean deleteDealer(Long dealerId) {
        return baseMapper.deleteDealer(dealerId)>0;
    }
}
