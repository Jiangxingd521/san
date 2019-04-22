# 会员积分规则(t_member_point_rule)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|rule_type|varchar(20)||是|积分类型|
|rule_value|int(11)||是|积分分值|
|user_id|bigint(20)||是||
|idata1|int(11)||是||
|idata2|int(11)||是||
|idata3|int(11)||是||
|idata4|int(11)||是||
|sdata1|varchar(255)||是||
|sdata2|varchar(255)||是||
|sdata3|varchar(255)||是||
|sdata4|varchar(255)||是||
|create_time|datetime||是||
|update_time|datetime||是||
