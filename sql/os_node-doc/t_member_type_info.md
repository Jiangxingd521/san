# 会员类型(t_member_type_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|type_name|varchar(255)||是|会员类型名称|
|type_rule_id|bigint(255)||是|升级规则|
|type_quanty|int(255)||是|升级数据量|
|user_id|bigint(20)||是|操作人|
|idata1|int(255)||是||
|idata2|int(255)||是||
|idata3|int(255)||是||
|idata4|int(255)||是||
|sdata1|varchar(255)||是||
|sdata2|varchar(255)||是||
|sdata3|varchar(255)||是||
|sdata4|varchar(255)||是||
|create_time|datetime||是||
|update_time|datetime||是||
