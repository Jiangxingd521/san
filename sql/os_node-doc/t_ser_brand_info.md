# 企业品牌信息(t_ser_brand_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|brand_name|varchar(255)||是|品牌名称|
|short_title|varchar(255)||是|简略标题|
|brand_sort|int(255)||是|排序|
|region_id|bigint(255)||是|产地|
|brand_keyword|varchar(255)||是|品牌关键字|
|brand_remark|varchar(1024)||是|品牌备注|
|brand_state|int(255)||是|品牌状态（0：使用，1：未使用）|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
