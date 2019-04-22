package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.output.vo.web.base.BrandSeriesProductVo;
import com.ningyang.os.action.output.vo.web.base.CodeImportTemplateVo;
import com.ningyang.os.action.utils.ReadFileBackData;
import com.ningyang.os.dao.SerCodeImportTempInfoMapper;
import com.ningyang.os.pojo.SerCodeImportTempInfo;
import com.ningyang.os.pojo.SerGoodsInfo;
import com.ningyang.os.service.ISerBrandSeriesProductInfoService;
import com.ningyang.os.service.ISerCodeImportTempInfoService;
import com.ningyang.os.service.ISerCodeImportTemplateInfoService;
import com.ningyang.os.service.ISerGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.getOrderNum;

/**
 * <p>
 * 溯源码导入临时表 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
@Service
public class SerCodeImportTempInfoServiceImpl extends ServiceImpl<SerCodeImportTempInfoMapper, SerCodeImportTempInfo> implements ISerCodeImportTempInfoService {

    @Autowired
    private ISerGoodsInfoService goodsInfoService;
    @Autowired
    private ISerCodeImportTemplateInfoService templateInfoService;
    @Autowired
    private ISerBrandSeriesProductInfoService productInfoService;


    @Override
    public boolean add(List<ReadFileBackData> listData, Long templateId) {
        //获取模板信息
        CodeImportTemplateVo templateVo = templateInfoService.findCodeImportTemplateVo(templateId);
//        System.out.println(JSONObject.toJSONString(templateVo));
        boolean flag;
        Long leftCodeType = templateVo.getLeftCodeType();
//        System.out.println("leftCodeType:"+leftCodeType);
        if (leftCodeType == 1) {
            //模板对应的品牌系列产品
            BrandSeriesProductVo vo = productInfoService.findBrandSeriesProductVo(templateVo.getProductId());
            //内码
            List<SerGoodsInfo> listTemp = new ArrayList<>();
            for (ReadFileBackData data : listData) {
                SerGoodsInfo info = new SerGoodsInfo();
                info.setBrandId(vo.getBrandId());
                info.setBrandName(templateVo.getBrandName());
                info.setBrandSeriesId(vo.getSeriesId());
                info.setBrandSeriesName(templateVo.getSeriesName());
                info.setBrandSeriesProductId(vo.getProductId());
                info.setBrandSeriesProductName(templateVo.getProductName());
                info.setM1(data.getLData());
                int rightCodeId = Integer.parseInt(templateVo.getRightCodeId().toString());
                switch (rightCodeId){
                    case 2:
                        info.setM2(data.getRData());
                        break;
                    case 3:
                        info.setM3(data.getRData());
                        break;
                    case 4:
                        info.setM4(data.getRData());
                        break;
                    case 5:
                        info.setM5(data.getRData());
                        break;
                    default:
                        break;
                }
                /*info.setM1Remark(templateVo.getLeftCodeName());
                info.setM2Remark(templateVo.getRightCodeName());*/
                info.setCreateTime(new Date());
                info.setUpdateTime(new Date());
                info.setGoodsState(0);
                listTemp.add(info);
            }
//            System.out.println(JSONObject.toJSONString(listTemp));
            flag = goodsInfoService.saveBatch(listTemp);
        } else {
            //外码
            List<SerCodeImportTempInfo> listTemp = new ArrayList<>();
            for (ReadFileBackData data : listData) {
                SerCodeImportTempInfo info = new SerCodeImportTempInfo();
                info.setTemplateId(templateId);
                info.setLeftCode(data.getLData());
                info.setRightCode(data.getRData());
                info.setImportNo(getOrderNum());
                info.setLeftCodeType(templateVo.getLeftCodeId());
                info.setRightCodeType(templateVo.getRightCodeId());
                listTemp.add(info);
            }
//            System.out.println(JSONObject.toJSONString(listTemp));
            //插入临时表
            flag = saveBatch(listTemp);
        }
        return flag;
    }


    @Override
    public void callSetCode() {
        baseMapper.callSetCode();
    }
}
