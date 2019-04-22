package com.ningyang.os.action.input.command.web.serve;

import com.ningyang.os.action.output.dto.serve.RegisterCodeDto;
import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/19 14:54
 * @描述：授权码
 */
@Data
public class RegisterCodeCommand {

    private List<RegisterCodeDto> codeVoList;
}
