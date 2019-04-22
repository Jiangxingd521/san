# 角色权限表(t_sys_role_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(255)|PRI|否||
|role_name|varchar(255)||是|角色名称|
|role_state|int(255)||是|状态（0：有效，1：无效）|
|role_flag|int(255)||是|标识（0：其他，1：最高级）|
|create_time|timestamp||是|创建时间|
|update_time|timestamp||是|修改时间|
