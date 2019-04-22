package com.ningyang.os.action.utils;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @Author： kaider
 * @Date：2018/08/23 10:03
 * @描述：
 */
public class ReadFileBackData {
    //左列数据
    @Excel(name = "LData")
    private String LData;
    //右列数据
    @Excel(name = "RData")
    private String RData;

    public String getLData() {
        return LData;
    }

    public void setLData(String LData) {
        this.LData = LData;
    }

    public String getRData() {
        return RData;
    }

    public void setRData(String RData) {
        this.RData = RData;
    }
}
