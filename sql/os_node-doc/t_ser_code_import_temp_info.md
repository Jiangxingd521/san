# 溯源码导入临时表(t_ser_code_import_temp_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(255)|PRI|否||
|template_id|bigint(255)||是|模板id|
|product_id|bigint(20)||是|产品id|
|left_code|varchar(255)||是|左码|
|right_code|varchar(255)||是|右码|
|left_code_type|bigint(255)||是|左码码类型（盖内外盖之类）|
|right_code_type|bigint(255)||是|右码码类型（盖内外盖之类）|
|import_no|varchar(255)||是|导入的订单号|
|creat_time|datetime||是|创建时间|
|orderno|int(11)||是|排序用|
