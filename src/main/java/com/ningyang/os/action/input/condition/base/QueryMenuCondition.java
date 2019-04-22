package com.ningyang.os.action.input.condition.base;

import com.ningyang.os.action.input.condition.common.BaseCondition;
import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/09/28 16:50
 * @描述：菜单
 */
@Data
public class QueryMenuCondition extends BaseCondition {
    //导航栏状态
    private Integer barState;
    //菜单状态
    private String menuState;
    //用户id
    private String userId;
    //父id
    private String pid;
    //顺序
    private Integer sort;
    //导航栏id
    private Long barId = 0L;
    //菜单类型
    private Integer menuType = 0;
}
