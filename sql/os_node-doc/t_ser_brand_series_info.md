# 品牌系列信息(t_ser_brand_series_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|brand_id|bigint(20)|MUL|是|品牌id|
|brand_name|varchar(255)||是|品牌名称|
|series_name|varchar(255)||是|系列名称|
|short_title|varchar(255)||是|简略标题|
|key_word|varchar(255)||是|关键字|
|series_remark|varchar(255)||是|系列备注|
|series_state|int(255)||是|系列状态（0：使用，1：未使用）|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
