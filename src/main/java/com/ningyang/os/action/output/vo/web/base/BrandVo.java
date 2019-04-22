package com.ningyang.os.action.output.vo.web.base;

import com.ningyang.os.action.output.dto.web.FileUploadDto;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/13 14:37
 * @描述：品牌
 */
@Data
public class BrandVo {
    //品牌id
    private Long brandId;
    //品牌名称
    private String brandName;
    //品牌排序
    private int brandSort;
    //品牌说明
    private String brandRemark;
    //品牌状态
    private int brandState;
    //品牌logo文件
    private List<FileUploadDto> logoFile;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getBrandSort() {
        return brandSort;
    }

    public void setBrandSort(int brandSort) {
        this.brandSort = brandSort;
    }

    public String getBrandRemark() {
        return brandRemark;
    }

    public void setBrandRemark(String brandRemark) {
        this.brandRemark = brandRemark;
    }

    public int getBrandState() {
        return brandState;
    }

    public void setBrandState(int brandState) {
        this.brandState = brandState;
    }

    public List<FileUploadDto> getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(List<FileUploadDto> logoFile) {
        this.logoFile = logoFile;
    }
}
