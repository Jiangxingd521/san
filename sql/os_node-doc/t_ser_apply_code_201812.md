# 溯源码(t_ser_apply_code_201812)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否|主键|
|center_id|bigint(20)||是|中心id|
|code_content|varchar(255)||是|溯源码内容|
|code_order|varchar(255)||是|溯源码订单号|
|code_position|bigint(255)||是|溯源码位置|
|code_position_type|bigint(255)||是|溯源码位置类型|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
