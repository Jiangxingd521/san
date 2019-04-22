package com.ningyang.os.action.input.command.web.base;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/14 10:30
 * @描述：码类型
 */
@Data
public class CodeTypeCommand {

    private Long codeId;

    private String codeName;
    //码类型（1：内外码，2：码类型（条形码、二维码），3：码模板（盒内外））
    private int codeType;

    private int codeState;
}
