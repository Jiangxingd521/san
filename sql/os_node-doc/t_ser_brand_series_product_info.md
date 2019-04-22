# 品牌系列产品信息(t_ser_brand_series_product_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|series_id|bigint(20)|MUL|是|系列id|
|series_name|varchar(255)||是|系列名称|
|product_name|varchar(255)||是|产品名称|
|short_title|varchar(255)||是|简略标题|
|key_word|varchar(255)||是|关键字|
|code_number|int(11)||是|码数量|
|series_standard|varchar(255)||是|产品规格|
|market_price|decimal(10,2)||是|市场价格|
|sales_price|decimal(10,2)||是|销售价格|
|code_69|varchar(255)||是|产品69码|
|product_remark|varchar(255)||是|系列备注|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
|product_state|int(255)||是|产品状态（0：使用，1：未使用）|
