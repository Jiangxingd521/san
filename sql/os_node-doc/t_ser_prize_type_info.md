# 奖项类型(t_ser_prize_type_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|prize_type_id|bigint(20)|PRI|否||
|prize_type_code|varchar(10)||否|奖项类型编码|
|prize_type_name|varchar(20)||是|奖项类型名称|
|prize_type_content|varchar(255)||是|奖项类型内容|
|user_id|bigint(20)||是|操作人|
|idata1|int(11)||是|奖项类型状态（0：启用，1：停用）|
|idata2|int(11)||是||
|idata3|int(11)||是||
|idata4|int(11)||是||
|sdata1|varchar(255)||是||
|sdata2|varchar(255)||是||
|sdata3|varchar(255)||是||
|sdata4|varchar(255)||是||
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
