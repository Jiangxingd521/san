package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.BrandCommand;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.dto.web.FileUploadDto;
import com.ningyang.os.action.output.vo.web.base.BrandVo;
import com.ningyang.os.dao.SerBrandInfoMapper;
import com.ningyang.os.pojo.SerBrandInfo;
import com.ningyang.os.pojo.SerBrandLogoFile;
import com.ningyang.os.pojo.SysFileInfo;
import com.ningyang.os.service.ISerBrandInfoService;
import com.ningyang.os.service.ISerBrandLogoFileService;
import com.ningyang.os.service.ISysFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 企业品牌信息 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@Service
public class SerBrandInfoServiceImpl extends ServiceImpl<SerBrandInfoMapper, SerBrandInfo> implements ISerBrandInfoService {

    @Autowired
    private ISerBrandLogoFileService logoFileService;
    @Autowired
    private ISysFileInfoService fileService;

    @Override
    public Page<BrandVo> findBrandVoPageByCondition(QueryBrandSeriesProductCondition condition) {
        Page<BrandVo> pageVo = new Page<>();
        List<BrandVo> listVoTemp = baseMapper.selectBrandVoPageByCondition(condition);
        for (BrandVo vo : listVoTemp) {
            SerBrandLogoFile logoFile = logoFileService.getOne(new QueryWrapper<SerBrandLogoFile>()
                    .eq("brand_id", vo.getBrandId()));
            List<FileUploadDto> fileList = new ArrayList<>();
            if (logoFile != null) {
                SysFileInfo fileInfo = fileService.getById(logoFile.getFileId());
                FileUploadDto dto = new FileUploadDto();
                dto.setId(logoFile.getFileId());
                dto.setName(fileInfo.getFileName());
                dto.setStatus("success");
                dto.setUid(logoFile.getFileId());
                dto.setUrl(fileInfo.getFilePath());
                fileList.add(dto);
            }
            vo.setLogoFile(fileList);
        }
        long total = baseMapper.selectBrandVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public List<BrandVo> findBrandVoByCondition(QueryBrandSeriesProductCondition condition) {
        List<BrandVo> list = baseMapper.selectBrandVoByCondition(condition);
        for (BrandVo vo : list) {
            SerBrandLogoFile logoFile = logoFileService.getOne(new QueryWrapper<SerBrandLogoFile>()
                    .eq("brand_id", vo.getBrandId()));
            List<FileUploadDto> fileList = new ArrayList<>();
            if (logoFile != null) {
                SysFileInfo fileInfo = fileService.getById(logoFile.getFileId());
                FileUploadDto dto = new FileUploadDto();
                dto.setId(logoFile.getFileId());
                dto.setName(fileInfo.getFileName());
                dto.setStatus("success");
                dto.setUid(logoFile.getFileId());
                dto.setUrl(fileInfo.getFilePath());
                fileList.add(dto);
            }
            vo.setLogoFile(fileList);
        }
        return list;
    }


    @Override
    public boolean addOrUpdate(BrandCommand command) {
        SerBrandInfo info = getOne(new QueryWrapper<SerBrandInfo>().eq("id", command.getBrandId()));
        boolean flag1;
        if (info != null) {
            info.setBrandName(command.getBrandName());
            info.setBrandSort(command.getBrandSort());
            info.setBrandRemark(command.getBrandRemark());
            info.setBrandState(command.getBrandState());
            info.setUpdateTime(new Date());
            flag1 = updateById(info);
        } else {
            info = new SerBrandInfo();
            info.setBrandName(command.getBrandName());
            info.setBrandSort(command.getBrandSort());
            info.setBrandRemark(command.getBrandRemark());
            info.setBrandState(0);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag1 = save(info);
        }

        logoFileService.remove(new QueryWrapper<SerBrandLogoFile>().eq("brand_id", command.getBrandId()));
        List<SerBrandLogoFile> fileList = new ArrayList<>();
        for (FileUploadDto dto : command.getLogoFile()) {
            SerBrandLogoFile logoFile = new SerBrandLogoFile();
            logoFile.setBrandId(info.getId());
            logoFile.setFileId(dto.getId());
            fileList.add(logoFile);
        }
        boolean flag2 = logoFileService.saveBatch(fileList);

        return flag1 && flag2;
    }
}
