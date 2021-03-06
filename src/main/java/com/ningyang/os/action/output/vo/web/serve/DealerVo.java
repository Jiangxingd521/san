package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.List;

/**
 * @Author： kaider
 * @Date：2018/11/14 16:54
 * @描述：经销商
 */
@Data
public class DealerVo {
    //
    private Long dealerId;
    //经销商
    private String dealerName;
    //联系人
    private String personName;
    //联系人电话
    private String personMobile;
    //社会码
    private String socialCode;
    //区域
    private List<String> regionList;
    //区域名称
    private String regionName;
    //详细地址
    private String address;
    //经销商状态（0：合作，1：不合作）
    private int dealerState;
    //备注
    private String dealerRemark;
    //经销商状态（0：启用，1：禁用）
    private int typeState;
    //经销商删除状态（0：未删除，1：已删除）
    private int dealerType;
}
