# 溯源码导入模板(t_ser_code_import_template_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|product_id|bigint(20)|MUL|是|产品id|
|template_name|varchar(255)||是|模板名称|
|left_code_type|bigint(255)||是|溯源左码类型（1：内码，2：外码）|
|left_code_type_id|bigint(20)|MUL|是|左码|
|right_code_type_id|bigint(20)|MUL|是|右码|
|template_remark|varchar(255)||是|模板说明|
|template_state|int(255)||是|模板状态（0：使用，1：未使用）|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
