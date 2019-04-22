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
 * 会员数据
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@TableName("t_member_info")
public class MemberInfo extends Model<MemberInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "open_id", type = IdType.INPUT)
    private String openId;
    /**
     * 会员类型
     */
    @TableField("member_type_id")
    private Long memberTypeId;
    /**
     * 支付id
     */
    @TableField("ali_pay_id")
    private String aliPayId;
    /**
     * 是否订阅微信号
     */
    private Integer subscribe;
    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 性别
     */
    private Integer sex;
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
    /**
     * 语言
     */
    private String language;
    /**
     * 头像
     */
    @TableField("head_img_url")
    private String headImgUrl;
    /**
     * 关注时间
     */
    @TableField("subscribe_time")
    private Date subscribeTime;
    /**
     * 微信唯一编码
     */
    @TableField("union_id")
    private String unionId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 分组id
     */
    @TableField("group_id")
    private String groupId;
    /**
     * 标签id
     */
    @TableField("tagid_list")
    private String tagidList;
    /**
     * 对应微信数据id
     */
    @TableField("wechat_app_id")
    private String wechatAppId;
    /**
     * 会员姓名
     */
    @TableField("member_name")
    private String memberName;
    /**
     * 会员手机号
     */
    @TableField("member_mobile")
    private String memberMobile;
    /**
     * 会员登录账号
     */
    @TableField("member_account")
    private String memberAccount;
    /**
     * 登录密码
     */
    @TableField("member_password")
    private String memberPassword;
    @TableField("user_id")
    private Long userId;
    private Integer idata1;
    private Integer idata2;
    private Integer idata3;
    private Integer idata4;
    private String sdata1;
    private String sdata2;
    private String sdata3;
    private String sdata4;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getMemberTypeId() {
        return memberTypeId;
    }

    public void setMemberTypeId(Long memberTypeId) {
        this.memberTypeId = memberTypeId;
    }

    public String getAliPayId() {
        return aliPayId;
    }

    public void setAliPayId(String aliPayId) {
        this.aliPayId = aliPayId;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList;
    }

    public String getWechatAppId() {
        return wechatAppId;
    }

    public void setWechatAppId(String wechatAppId) {
        this.wechatAppId = wechatAppId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIdata1() {
        return idata1;
    }

    public void setIdata1(Integer idata1) {
        this.idata1 = idata1;
    }

    public Integer getIdata2() {
        return idata2;
    }

    public void setIdata2(Integer idata2) {
        this.idata2 = idata2;
    }

    public Integer getIdata3() {
        return idata3;
    }

    public void setIdata3(Integer idata3) {
        this.idata3 = idata3;
    }

    public Integer getIdata4() {
        return idata4;
    }

    public void setIdata4(Integer idata4) {
        this.idata4 = idata4;
    }

    public String getSdata1() {
        return sdata1;
    }

    public void setSdata1(String sdata1) {
        this.sdata1 = sdata1;
    }

    public String getSdata2() {
        return sdata2;
    }

    public void setSdata2(String sdata2) {
        this.sdata2 = sdata2;
    }

    public String getSdata3() {
        return sdata3;
    }

    public void setSdata3(String sdata3) {
        this.sdata3 = sdata3;
    }

    public String getSdata4() {
        return sdata4;
    }

    public void setSdata4(String sdata4) {
        this.sdata4 = sdata4;
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
        return this.openId;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "openId=" + openId +
                ", memberTypeId=" + memberTypeId +
                ", aliPayId=" + aliPayId +
                ", subscribe=" + subscribe +
                ", nickName=" + nickName +
                ", sex=" + sex +
                ", city=" + city +
                ", country=" + country +
                ", province=" + province +
                ", language=" + language +
                ", headImgUrl=" + headImgUrl +
                ", subscribeTime=" + subscribeTime +
                ", unionId=" + unionId +
                ", remark=" + remark +
                ", groupId=" + groupId +
                ", tagidList=" + tagidList +
                ", wechatAppId=" + wechatAppId +
                ", memberName=" + memberName +
                ", memberMobile=" + memberMobile +
                ", memberAccount=" + memberAccount +
                ", memberPassword=" + memberPassword +
                ", userId=" + userId +
                ", idata1=" + idata1 +
                ", idata2=" + idata2 +
                ", idata3=" + idata3 +
                ", idata4=" + idata4 +
                ", sdata1=" + sdata1 +
                ", sdata2=" + sdata2 +
                ", sdata3=" + sdata3 +
                ", sdata4=" + sdata4 +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
