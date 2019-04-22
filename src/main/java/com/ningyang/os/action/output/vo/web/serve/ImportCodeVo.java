package com.ningyang.os.action.output.vo.web.serve;

import lombok.Data;

import java.util.Date;

/**
 * @Author： kaider
 * @Date：2018/11/22 16:27
 * @描述：导入溯源码数据
 */
@Data
public class ImportCodeVo {

    private Long logId;
    //文件名称
    private String fileName;
    //上传订单
    private String uploadOrder;
    //溯源码数量
    private int codeCount;
    //操作人
    private String userName;
    //品牌
    private String brandName;
    //系列
    private String seriesName;
    //产品
    private String productName;
    //模板名称
    private String templateName;
    //
    private Date createTime;
    //上传时间
    private String createTimeStr;
}
