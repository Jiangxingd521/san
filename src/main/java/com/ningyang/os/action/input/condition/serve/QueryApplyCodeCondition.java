package com.ningyang.os.action.input.condition.serve;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/11/21 10:11
 * @描述：溯源码申请
 */
@Data
public class QueryApplyCodeCondition extends BaseCondition {
    //模板表名
    private String tableName;
    //溯源码订单
    private String codeOrder;
    //码类型
    private Integer codeType;
    //溯源码内容
    private String codeContent;
    //种类
    private String codeTypeName;
    //位置
    private String codePositionTypeName;
}
