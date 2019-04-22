package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/14 14:20
 * @描述：码模板
 */
@Data
public class CodeImportTemplateCommand {

    private Long templateId;

    private Long productId;

    private String templateName;

    private Long leftCodeType;

    private Long leftCodeId;

    private Long rightCodeId;

    private String templateRemark;

    private int templateState;
}
