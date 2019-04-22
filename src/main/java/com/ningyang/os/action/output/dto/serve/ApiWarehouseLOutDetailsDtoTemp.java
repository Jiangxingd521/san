package com.ningyang.os.action.output.dto.serve;

import lombok.Data;

/**
 * @Author： kaider
 * @Date：2018/12/11 18:05
 * @描述：
 */
@Data
public class ApiWarehouseLOutDetailsDtoTemp {
    //产品系列名称
    private String productName;
    //扫描到的多余箱数
    private int boxCount;
}
