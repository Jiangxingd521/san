package com.ningyang.os.action.enums;

/**
 * @Author： kaider
 * @Date：2018/07/25 12:37
 * @描述：系统错误枚举
 */
public enum SystemErrorEnum {
    LOGIN_ERROR(0, "登录信息有误,请重新登录"),
    PASSWORD_ERROR(1, "用户名或密码错误！"),
    EDIT_PASSWORD_ERROR(2, "密码修改失败"),
    OPERATING_ERROR(3, "操作失败"),
    ADD_ERROR(4, "添加失败"),
    DELETE_ERROR(5, "删除失败"),
    EDIT_ERROR(6, "修改失败"),
    OPERATING_PERMISSION_ERROR(7, "授权失败"),
    PERMISSION_ERROR(8, "权限不足"),
    APPLICATION_ERROR(9, "申请失败"),
    USER_TYPE_ERROR(10, "用户类型不正确"),
    SEND_ERROR(11, "发送失败"),
    ENTERPRISE_REGISTER_ERROR(12, "企业信息未注册"),
    LINK_CENTER_ERROR(13, "链接失败,请联系管理员"),
    USER_DATA_ERROR(14, "用户信息错误"),
    USED_MOBILE_ERROR(15, "手机号码已注册"),
    RELEASE_ERROR(16, "发布失败"),
    DATA_ERROR(17, "数据有误"),
    API_REQUEST_ERROR(18, "接口请求失败"),
    SEND_REQUEST_ERROR(19, "申请未通过"),
    IMPORT_DATA_ERROR(20, "数据导入失败"),
    PUTIN_WAREHOUSE_ERROR(21, "入库失败"),
    PUTOUT_WAREHOUSE_ERROR(22, "出库失败"),
    SALES_RETURN_WAREHOUSE_ERROR(23, "退货失败");

    private Integer code;
    private String info;

    SystemErrorEnum(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public static SystemErrorEnum parseCode(Integer code) {
        for (SystemErrorEnum each : SystemErrorEnum.values()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }

}
