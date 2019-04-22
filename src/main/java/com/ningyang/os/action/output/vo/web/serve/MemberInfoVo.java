package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.Date;

/**
 * @Author： kaider
 * @Date：2018/12/06 09:53
 * @描述：会员信息
 */
@Data
public class MemberInfoVo {

    private String nickName;

    private int sex;
    /**
     * 城市
     */
    private String city;
    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;

    private Date createTime;

    private String createTimeStr;
}
