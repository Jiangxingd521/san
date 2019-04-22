# 会员数据(t_member_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|open_id|varchar(32)|PRI|否||
|member_type_id|bigint(20)||是|会员类型|
|ali_pay_id|varchar(32)||是|支付id|
|subscribe|int(11)||否|是否订阅微信号|
|nick_name|varchar(50)||否|昵称|
|sex|int(11)||否|性别|
|city|varchar(32)||否|城市|
|country|varchar(32)||否|国家|
|province|varchar(32)||否|省份|
|language|varchar(8)||是|语言|
|head_img_url|varchar(500)||是|头像|
|subscribe_time|datetime||否|关注时间|
|union_id|varchar(32)||是|微信唯一编码|
|remark|varchar(1024)||是|备注|
|group_id|varchar(32)||是|分组id|
|tagid_list|varchar(32)||是|标签id|
|wechat_app_id|varchar(255)||是|对应微信数据id|
|member_name|varchar(50)||是|会员姓名|
|member_mobile|varchar(20)||是|会员手机号|
|member_account|varchar(50)||是|会员登录账号|
|member_password|varchar(20)||是|登录密码|
|user_id|bigint(20)||是||
|idata1|int(11)||是||
|idata2|int(11)||是||
|idata3|int(11)||是||
|idata4|int(11)||是||
|sdata1|varchar(255)||是||
|sdata2|varchar(255)||是||
|sdata3|varchar(255)||是||
|sdata4|varchar(255)||是||
|create_time|datetime||是||
|update_time|datetime||是||
