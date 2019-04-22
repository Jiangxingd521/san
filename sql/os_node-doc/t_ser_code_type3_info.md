# 溯源码类型(t_ser_code_type3_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|code_name|varchar(255)||是|码类型名称|
|code_state|int(255)||是|类型状态（0：使用，1：未使用）|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
