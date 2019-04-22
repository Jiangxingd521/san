package com.ningyang.os.action.input.command.web.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/22 14:59
 * @描述：溯源码导入数据
 */
@Data
public class ImportCodeCommand {
    //上传的文件名称
    private String importFileName;
    //
    private String saveFilePath;
    //操作人
    private Long userId;
    //导入的数量
    private Long codeCount;
    //模板id
    private Long templateId;
}
