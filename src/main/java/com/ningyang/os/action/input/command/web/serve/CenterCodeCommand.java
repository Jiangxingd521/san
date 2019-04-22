package com.ningyang.os.action.input.command.web.serve;

import com.ningyang.os.action.input.command.base.ApiCommand;
import com.ningyang.os.action.output.dto.serve.CenterCodeDto;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/20 12:06
 * @描述：溯源码
 */
@Data
public class CenterCodeCommand extends ApiCommand {
    //申请状态
    private int applyState;
    //码订单
    private String codeOrder;
    //溯源码标识
    private String codeFlag;
    //溯源码位置
    private int codePositionId;
    //溯源码位置类型
    private int codePositionTypeId;
    //溯源码
    private List<CenterCodeDto> codeVoList;
}
