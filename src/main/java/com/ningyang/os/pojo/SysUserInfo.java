package com.ningyang.os.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@TableName("t_sys_user_info")
public class SysUserInfo extends Model<SysUserInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 登录名
     */
    @TableField("login_name")
    private String loginName;
    /**
     * 登录密码
     */
    @TableField("login_password")
    private String loginPassword;
    /**
     * 密码明文(可设置可不设置)
     */
    @TableField("login_password_plaintext")
    private String loginPasswordPlaintext;
    /**
     * 用户状态（0：有效，1：无效，2：禁用）
     */
    @TableField("user_state")
    private Integer userState;
    /**
     * 用户类型（0：系统用户，1：其他）
     */
    @TableField("user_type")
    private Integer userType;
    /**
     * 授权码
     */
    @TableField("authorization_code")
    private String authorizationCode;
    /**
     * 创建人
     */
    @TableField("create_user_id")
    private Long createUserId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginPasswordPlaintext() {
        return loginPasswordPlaintext;
    }

    public void setLoginPasswordPlaintext(String loginPasswordPlaintext) {
        this.loginPasswordPlaintext = loginPasswordPlaintext;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysUserInfo{" +
                "id=" + id +
                ", pid=" + pid +
                ", userName=" + userName +
                ", loginName=" + loginName +
                ", loginPassword=" + loginPassword +
                ", loginPasswordPlaintext=" + loginPasswordPlaintext +
                ", userState=" + userState +
                ", userType=" + userType +
                ", authorizationCode=" + authorizationCode +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
