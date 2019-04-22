package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.ProductCommand;
import com.ningyang.os.action.input.condition.base.QueryBrandSeriesProductCondition;
import com.ningyang.os.action.output.dto.serve.ProductCodeDto;
import com.ningyang.os.action.output.vo.api.ApiBrandSeriesProductVo;
import com.ningyang.os.action.output.vo.api.ApiProductVo;
import com.ningyang.os.action.output.vo.api.ApiSeriesVo;
import com.ningyang.os.action.output.vo.web.base.*;
import com.ningyang.os.action.output.vo.web.serve.BrandSeriesProductNameVo;
import com.ningyang.os.dao.SerBrandSeriesProductInfoMapper;
import com.ningyang.os.pojo.SerBrandSeriesProductCodeInfo;
import com.ningyang.os.pojo.SerBrandSeriesProductInfo;
import com.ningyang.os.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * <p>
 * 品牌系列产品信息 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
@Service
public class SerBrandSeriesProductInfoServiceImpl extends ServiceImpl<SerBrandSeriesProductInfoMapper, SerBrandSeriesProductInfo> implements ISerBrandSeriesProductInfoService {

    @Autowired
    private ISerBrandSeriesProductFileService productFileService;
    @Autowired
    private ISerBrandSeriesProductCodeInfoService codeInfoService;
    @Autowired
    private ISysFileInfoService fileService;
    @Autowired
    private ISerBrandInfoService brandInfoService;
    @Autowired
    private ISerBrandSeriesInfoService seriesInfoService;


    @Override
    public Page<ProductVo> findProductVoPageByCondition(QueryBrandSeriesProductCondition condition) {
        Page<ProductVo> pageVo = new Page<>();
        List<ProductVo> listVoTemp = baseMapper.selectProductVoPageByCondition(condition);
        for (ProductVo vo : listVoTemp) {
            /*List<Long> productCodeIdList = codeInfoService.getProductCodeIds(vo.getProductId());
            int codeNumber = productCodeIdList.size();
            vo.setCodeNumber(codeNumber);
            vo.setCodeTypeIds(productCodeIdList);*/

            List<Long> productCodeIdList = new ArrayList<>();
            List<ProductCodeDto> productCodeDtoList = codeInfoService.getProductCodeMake(vo.getProductId());
            List<String> codeMakeInfoList = new ArrayList<>();
            for(ProductCodeDto dto : productCodeDtoList){
                productCodeIdList.add(dto.getCodeId());
                codeMakeInfoList.add(dto.getCodeName());
            }
            int codeNumber = productCodeDtoList.size();
            vo.setCodeNumber(codeNumber);
            vo.setCodeTypeIds(productCodeIdList);
            String codeMakeInfo = join(codeMakeInfoList, "、");
            vo.setCodeMakeInfo(codeMakeInfo);

            /*List<SerBrandSeriesProductFile> productFileList = productFileService.list(new QueryWrapper<SerBrandSeriesProductFile>()
                    .eq("product_id", vo.getProductId()));

            List<FileUploadDto> fileList = new ArrayList<>();
            for (SerBrandSeriesProductFile productFile : productFileList) {
                Long fileId = productFile.getFileId();
                SysFileInfo fileInfo = fileService.getById(fileId);
                FileUploadDto dto = new FileUploadDto();
                dto.setId(fileId);
                dto.setName(fileInfo.getFileName());
                dto.setStatus("success");
                dto.setUid(fileId);
                dto.setUrl(fileInfo.getFilePath());
                fileList.add(dto);
            }
            vo.setProductFileList(fileList);*/
        }
        long total = baseMapper.selectProductVoPageCountByCondition(condition);
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public List<ProductVo> findProductVoByCondition(QueryBrandSeriesProductCondition condition) {
        List<ProductVo> list = baseMapper.selectProductVoByCondition(condition);
        for (ProductVo vo : list) {
            List<Long> productCodeIdList = codeInfoService.getProductCodeIds(vo.getProductId());
            int codeNumber = productCodeIdList.size();
            vo.setCodeNumber(codeNumber);
            vo.setCodeTypeIds(productCodeIdList);

            /*List<SerBrandSeriesProductFile> productFileList = productFileService.list(new QueryWrapper<SerBrandSeriesProductFile>()
                    .eq("product_id", vo.getProductId()));

            List<FileUploadDto> fileList = new ArrayList<>();
            for (SerBrandSeriesProductFile productFile : productFileList) {
                Long fileId = productFile.getFileId();
                SysFileInfo fileInfo = fileService.getById(fileId);
                FileUploadDto dto = new FileUploadDto();
                dto.setId(fileId);
                dto.setName(fileInfo.getFileName());
                dto.setStatus("success");
                dto.setUid(fileId);
                dto.setUrl(fileInfo.getFilePath());
                fileList.add(dto);
            }
            vo.setProductFileList(fileList);*/
        }
        return list;
    }

    @Override
    public boolean addOrUpdate(ProductCommand command) {
        SerBrandSeriesProductInfo info = getOne(new QueryWrapper<SerBrandSeriesProductInfo>().eq("id", command.getProductId()));
        boolean flag1;
        if (info != null) {
            info.setSeriesId(command.getSeriesId());
            info.setProductName(command.getProductName());
            info.setSeriesStandard(command.getSeriesStandard());
            info.setMarketPrice(command.getMarketPrice());
            info.setSalesPrice(command.getSalesPrice());
            info.setCode69(command.getCode69());
            info.setProductRemark(command.getProductRemark());
            info.setProductState(command.getProductState());
            info.setUpdateTime(new Date());
            flag1 = updateById(info);
        } else {
            info = new SerBrandSeriesProductInfo();
            info.setSeriesId(command.getSeriesId());
            info.setProductName(command.getProductName());
            info.setSeriesStandard(command.getSeriesStandard());
            info.setMarketPrice(command.getMarketPrice());
            info.setSalesPrice(command.getSalesPrice());
            info.setCode69(command.getCode69());
            info.setProductRemark(command.getProductRemark());
            info.setProductState(0);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            flag1 = save(info);
        }

        codeInfoService.remove(new QueryWrapper<SerBrandSeriesProductCodeInfo>().eq("product_id", info.getId()));
        List<SerBrandSeriesProductCodeInfo> productCodeInfoList = new ArrayList<>();
        for (Long codeTypeId : command.getCodeTypeIds()) {
            SerBrandSeriesProductCodeInfo productCodeInfo = new SerBrandSeriesProductCodeInfo();
            productCodeInfo.setProductId(info.getId());
            productCodeInfo.setCodeId(codeTypeId);
            productCodeInfoList.add(productCodeInfo);
        }
        boolean flag2 = codeInfoService.saveBatch(productCodeInfoList);

       /* productFileService.remove(new QueryWrapper<SerBrandSeriesProductFile>().eq("product_id", info.getId()));
        List<SerBrandSeriesProductFile> fileList = new ArrayList<>();
        for (FileUploadDto fileDto : command.getProductFileList()) {
            SerBrandSeriesProductFile productFile = new SerBrandSeriesProductFile();
            productFile.setProductId(info.getId());
            productFile.setFileId(fileDto.getId());
            fileList.add(productFile);
        }
        boolean flag3 = productFileService.saveBatch(fileList);*/

        return flag1 && flag2;
    }

    @Override
    public BrandSeriesProductVo findBrandSeriesProductVo(Long productId) {
        return baseMapper.selectBrandSeriesProductVo(productId);
    }

    @Override
    public List<CodeTypeVo> findCodeTypeVoByCondition(QueryBrandSeriesProductCondition condition) {
        return baseMapper.selectCodeTypeVoByCondition(condition);
    }

    @Override
    public List<ApiBrandSeriesProductVo> findApiBrandSeriesProductVoCondition() {
        QueryBrandSeriesProductCondition condition = new QueryBrandSeriesProductCondition();
        List<ApiBrandSeriesProductVo> brandSeriesProductVoList = new ArrayList<>();
        condition.setBrandState(0);
        List<BrandVo> brandList = brandInfoService.findBrandVoByCondition(condition);
        for (BrandVo brandVo : brandList) {
            ApiBrandSeriesProductVo apiBrandSeriesProductVo = new ApiBrandSeriesProductVo();
            apiBrandSeriesProductVo.setBrandId(brandVo.getBrandId());
            apiBrandSeriesProductVo.setBrandName(brandVo.getBrandName());
            List<ApiSeriesVo> seriesListVo = new ArrayList<>();
            condition.setBrandId(brandVo.getBrandId());
            condition.setSeriesState(0);
            List<SeriesVo> seriesList = seriesInfoService.findSeriesVoByCondition(condition);
            for (SeriesVo seriesVo : seriesList) {
                ApiSeriesVo apiSeriesVo = new ApiSeriesVo();
                apiSeriesVo.setSeriesId(seriesVo.getSeriesId());
                apiSeriesVo.setSeriesName(seriesVo.getSeriesName());
                List<ApiProductVo> productListVo = new ArrayList<>();
                List<SerBrandSeriesProductInfo> productInfoList = list(new QueryWrapper<SerBrandSeriesProductInfo>()
                        .eq("series_id", seriesVo.getSeriesId())
                        .eq("product_state", 0));
                for (SerBrandSeriesProductInfo productInfo : productInfoList) {
                    ApiProductVo productVo = new ApiProductVo();
                    productVo.setProductId(productInfo.getId());
                    productVo.setProductName(productInfo.getProductName());
                    productListVo.add(productVo);
                }
                apiSeriesVo.setProductListVo(productListVo);
                seriesListVo.add(apiSeriesVo);
            }
            apiBrandSeriesProductVo.setSeriesListVo(seriesListVo);
            brandSeriesProductVoList.add(apiBrandSeriesProductVo);
        }
        return brandSeriesProductVoList;
    }

    @Override
    public List<SeriesProductVo> findSeriesProductVoByCondition(QueryBrandSeriesProductCondition condition) {
        return baseMapper.selectSeriesProductVoByCondition(condition);
    }

    @Override
    public List<ApiProductVo> findApiProductVoList() {
        return baseMapper.selectApiProductVoList();
    }

    @Override
    public BrandSeriesProductNameVo findBrandSeriesProductNameVo(Long productId) {
        return baseMapper.selectBrandSeriesProductNameVo(productId);
    }
}
