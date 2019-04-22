package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.CodeImportTemplateCommand;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.output.vo.web.base.CodeImportTemplateVo;
import com.ningyang.os.action.output.vo.web.serve.BrandSeriesProductNameVo;
import com.ningyang.os.dao.SerCodeImportTemplateInfoMapper;
import com.ningyang.os.pojo.SerCodeImportTemplateInfo;
import com.ningyang.os.service.ISerBrandSeriesProductInfoService;
import com.ningyang.os.service.ISerCodeImportTemplateInfoService;
import com.ningyang.os.service.ISerCodeType3InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 码导入模板 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@Service
public class SerCodeImportTemplateInfoServiceImpl extends ServiceImpl<SerCodeImportTemplateInfoMapper, SerCodeImportTemplateInfo> implements ISerCodeImportTemplateInfoService {

    @Autowired
    private ISerBrandSeriesProductInfoService productInfoService;
    @Autowired
    private ISerCodeType3InfoService codeType3InfoService;


    @Override
    public Page<CodeImportTemplateVo> findCodeImportVoPageByCondition(QueryCodeCondition condition) {
        Page<CodeImportTemplateVo> pageVo = new Page<>();
        List<CodeImportTemplateVo> listVoTemp = baseMapper.selectCodeImportVoPageByCondition(condition);
        long total = baseMapper.selectCodeImportVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean addOrUpdate(CodeImportTemplateCommand command) {
        SerCodeImportTemplateInfo info = getOne(new QueryWrapper<SerCodeImportTemplateInfo>()
                .eq("id", command.getTemplateId()));
        boolean flag;
        if (info != null) {
            info.setProductId(command.getProductId());
            //设置模板名称
            String templateName = getTemplateName(command.getProductId(),command.getLeftCodeId(),command.getRightCodeId());
            info.setTemplateName(templateName);
            if (command.getLeftCodeId() == 1) {
                info.setLeftCodeType(1L);
            } else {
                info.setLeftCodeType(2L);
            }
            info.setLeftCodeTypeId(command.getLeftCodeId());
            info.setRightCodeTypeId(command.getRightCodeId());
            info.setTemplateRemark(command.getTemplateRemark());
            info.setTemplateState(command.getTemplateState());
            info.setUpdateTime(new Date());
            flag = updateById(info);
        } else {
            info = new SerCodeImportTemplateInfo();
            info.setProductId(command.getProductId());
            String templateName = getTemplateName(command.getProductId(),command.getLeftCodeId(),command.getRightCodeId());
            info.setTemplateName(templateName);
            if (command.getLeftCodeId() == 1) {
                info.setLeftCodeType(1L);
            } else {
                info.setLeftCodeType(2L);
            }
            info.setLeftCodeTypeId(command.getLeftCodeId());
            info.setRightCodeTypeId(command.getRightCodeId());
            info.setTemplateRemark(command.getTemplateRemark());
            info.setTemplateState(0);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag = save(info);
        }
        return flag;
    }

    @Override
    public CodeImportTemplateVo findCodeImportTemplateVo(Long templateId) {
        return baseMapper.selectCodeImportTemplateVo(templateId);
    }

    private String getTemplateName(Long productId,Long leftCodeId, Long rightCodeId){
        BrandSeriesProductNameVo vo = productInfoService.findBrandSeriesProductNameVo(productId);
        String leftName = codeType3InfoService.getById(leftCodeId).getCodeName();
        String rightName = codeType3InfoService.getById(rightCodeId).getCodeName();
        String templateName = vo.getBrandName()+'-'+vo.getSeriesName()+'-'+vo.getProductName()+'-'+leftName+'-'+rightName;
        return templateName;
    }
}
