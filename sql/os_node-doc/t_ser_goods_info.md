# 商品信息表(t_ser_goods_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(255)|PRI|否||
|brand_id|bigint(255)||否|品牌|
|brand_name|varchar(255)||是|品牌名称|
|brand_series_id|bigint(255)||否|系列|
|brand_series_name|varchar(255)||是|系列名称|
|brand_series_product_id|bigint(255)||否|产品|
|brand_series_product_name|varchar(255)||是|产品名称|
|M1|varchar(255)|UNI|否|内码|
|M2|varchar(255)||是|外码|
|M3|varchar(255)||是|外码|
|M4|varchar(255)||是|外码|
|M5|varchar(255)||是|外码|
|M6|varchar(255)||是|外码|
|M7|varchar(255)||是|外码|
|M8|varchar(255)||是|外码|
|M9|varchar(255)||是|外码|
|M10|varchar(255)||是|外码|
|M1_remark|varchar(255)||是|码说明|
|M2_remark|varchar(255)||是|码说明|
|M3_remark|varchar(255)||是|码说明|
|M4_remark|varchar(255)||是|码说明|
|M5_remark|varchar(255)||是|码说明|
|M6_remark|varchar(255)||是|码说明|
|M7_remark|varchar(255)||是|码说明|
|M8_remark|varchar(255)||是|码说明|
|M9_remark|varchar(255)||是|码说明|
|M10_remark|varchar(255)||是|码说明|
|goods_state|int(255)||是|商品状态（0：未入库，1：入库，2：出库，3：已扫描兑奖，4：丢失）|
|prize_set_uuid|varchar(255)||是|布奖设置时受影响的uuid|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
