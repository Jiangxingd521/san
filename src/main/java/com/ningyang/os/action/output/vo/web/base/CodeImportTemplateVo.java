package com.ningyang.os.action.output.vo.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/14 14:30
 * @描述：码模板
 */
@Data
public class CodeImportTemplateVo {

    private Long templateId;

    private String templateName;

    private Long productId;

    private String productName;

    private String seriesName;

    private String brandName;

    private Long brandId;

    private Long seriesId;

    private Long leftCodeType;

    private Long leftCodeId;

    private String leftCodeName;

    private Long rightCodeId;

    private String rightCodeName;

    private String templateRemark;

    private int templateState;
}
