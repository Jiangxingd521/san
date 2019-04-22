# 奖项设定(t_ser_prize_set_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|prize_set_id|bigint(20)|PRI|否||
|prize_manager_id|bigint(20)|MUL|是|奖项管理id|
|prize_set_name|varchar(255)||是|布奖名称|
|prod_id|bigint(20)||是|产品id|
|member_type|bigint(11)||是|会员类型|
|region_id|bigint(20)||是|布奖区域|
|prize_quantity|int(11)||是|布奖数量|
|money|decimal(5,2)||是|红包额度|
|money_end|decimal(5,2)||是|红包额度结束|
|ponit|int(11)||是|积分额度|
|point_end|int(11)||是|积分额度结束|
|prize_set_type|int(11)||是|布奖类型(1：限制数量，2：不限制数量)|
|prize_mode_type|int(11)||是|布奖模式(1：随机，2：平均)|
|card_money|int(11)||是|卡券消费额度|
|card_coupon_money|int(11)||是|卡券优惠额度|
|prize_start_date|datetime||是|布奖开始日期|
|prize_end_date|datetime||是|布奖结束日期|
|user_id|bigint(20)||是|操作人|
|idata1|int(11)||是||
|idata2|int(11)||是||
|idata3|int(11)||是||
|idata4|int(11)||是||
|sdata1|varchar(255)||是||
|sdata2|varchar(255)||是||
|sdata3|varchar(255)||是||
|sdata4|varchar(255)||是||
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
