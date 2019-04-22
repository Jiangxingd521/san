package com.ningyang.os.action.input.command.web.serve;

import com.ningyang.os.pojo.SerApplyCodeTemplate;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/21 10:09
 * @描述：溯源码模板
 */
@Data
public class TemplateCodeCommand {

    private String tableName;

    private List<SerApplyCodeTemplate> list;
}
