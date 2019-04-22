# 奖项设定操作记录(t_ser_prize_set_record_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|order_id|bigint(20)||是|订单id|
|order_no|varchar(255)||是|订单号|
|brand_id|int(11)||是|品牌id|
|series_id|bigint(20)||是|系列id|
|product_id|bigint(20)||是|产品id|
|warehouse_id|bigint(20)||是|仓库id|
|prize_set_id|bigint(255)||是|奖项id|
|prize_set_type|int(255)||是|布奖种类（0：订单，1：产品）|
|prize_set_state|int(255)||是|当前奖项设置的状态（0：有效，1：无效）|
|prize_set_uuid|varchar(255)||是|奖项设置时的uuid|
|prize_set_count|int(255)||是|奖项设置时受影响的数量|
|create_user_id|bigint(20)||是|操作人|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
