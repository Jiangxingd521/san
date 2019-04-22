# 奖项管理(t_ser_prize_manager_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|prize_manage_id|bigint(20)|PRI|否||
|prize_type_id|bigint(20)|MUL|是|奖项类型|
|prize_name|varchar(50)||是|奖项名称|
|prize_content|varchar(1024)||是|奖项内容|
|prize_remark|varchar(5000)||是|奖项备注|
|user_id|bigint(20)||是|操作人|
|idata1|int(11)||是||
|idata2|int(11)||是||
|idata3|int(11)||是||
|idata4|int(11)||是||
|sdata1|varchar(255)||是||
|sdata2|varchar(255)||是||
|sdata3|varchar(255)||是||
|sdata4|varchar(255)||是||
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
