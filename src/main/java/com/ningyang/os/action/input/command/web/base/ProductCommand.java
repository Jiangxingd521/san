package com.ningyang.os.action.input.command.web.base;

import com.ningyang.os.action.output.dto.web.FileUploadDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/13 18:41
 * @描述：产品
 */
@Data
public class ProductCommand {

    private Long productId;

    private String productName;

    private Long seriesId;
    //码类型id
    private List<Long> codeTypeIds;

    private String seriesStandard;

    private BigDecimal marketPrice;

    private BigDecimal salesPrice;

    private String code69;

    private String productRemark;

    private int productState;

    private List<FileUploadDto> productFileList;
}
