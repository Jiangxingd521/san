package com.ningyang.os.action.output.vo.web.base;

import com.ningyang.os.action.output.dto.web.FileUploadDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/13 18:13
 * @描述：产品
 */
@Data
public class ProductVo {

    private String brandName;

    private Long seriesId;

    private String seriesName;

    private Long productId;

    private String productName;

    private int codeNumber;

    private String seriesStandard;

    private BigDecimal marketPrice;

    private BigDecimal salesPrice;

    private String code69;

    private String productRemark;

    private int productState;
    //码类型id
    private List<Long> codeTypeIds;
    //组成码
    private String codeMakeInfo;

    private List<FileUploadDto> productFileList;
}
