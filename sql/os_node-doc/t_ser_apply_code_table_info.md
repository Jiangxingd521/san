# 溯源码申请对应的存放表(t_ser_apply_code_table_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|code_flag|varchar(255)||是|溯源码标识|
|code_table_name|varchar(255)||是|溯源码存在的表|
|create_time|datetime||是||
|update_time|datetime||是||
