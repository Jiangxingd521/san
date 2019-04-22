package com.ningyang.os.action.output.dto.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/21 09:34
 * @描述：溯源码
 */
@Data
public class CenterCodeDto {
    //码id
    private Long centerCodeId;
    //订单
    private String codeOrder;
    //码内容
    private String codeContent;
}
