# 溯源码申请(t_ser_apply_code_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|code_order|varchar(255)||是|申请订单号|
|apply_user_id|bigint(20)||是|申请人|
|code_type_id|bigint(20)||是|溯源码种类|
|code_position_id|bigint(20)||是|溯源码类型|
|code_position_type_id|bigint(20)||是|溯源码位置类型|
|apply_count|int(255)||是|申请数量|
|apply_state|int(255)||是|申请状态|
|code_table_name|varchar(255)||是|溯源码存放表|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
