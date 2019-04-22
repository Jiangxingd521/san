package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.dao.SerDealerRegionInfoMapper;
import com.ningyang.os.pojo.SerDealerRegionInfo;
import com.ningyang.os.pojo.SysBaseRegionInfo;
import com.ningyang.os.service.ISerDealerRegionInfoService;
import com.ningyang.os.service.ISysBaseRegionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 经销商区域 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-15
 */
@Service
public class SerDealerRegionInfoServiceImpl extends ServiceImpl<SerDealerRegionInfoMapper, SerDealerRegionInfo> implements ISerDealerRegionInfoService {

    @Autowired
    private ISysBaseRegionInfoService regionInfoService;

    @Override
    public List<String> getDealerRegionList(Long dealerId) {
        List<String> regionList = new ArrayList<>();
        List<SerDealerRegionInfo> list = list(new QueryWrapper<SerDealerRegionInfo>().eq("dealer_id", dealerId));
        for (SerDealerRegionInfo regionInfo : list) {
            Long regionId = regionInfo.getRegionId();
            regionList.add(regionId + "");
        }
        return regionList;
    }

    @Override
    public String getDealerRegionName(Long dealerId) {
        String regionName = "";
        List<SerDealerRegionInfo> list = list(new QueryWrapper<SerDealerRegionInfo>().eq("dealer_id", dealerId));
        for (SerDealerRegionInfo dealerRegionInfo : list) {
            SysBaseRegionInfo regionInfo = regionInfoService.getById(dealerRegionInfo.getRegionId());
            if (regionName != "") {
                regionName = regionName + "/" + regionInfo.getRegionName();
            } else {
                regionName = regionInfo.getRegionName();
            }
        }
        return regionName;
    }
}
