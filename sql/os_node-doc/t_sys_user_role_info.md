# 用户关联角色(t_sys_user_role_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(255)|PRI|否||
|user_id|bigint(255)|MUL|是|用户id|
|role_id|bigint(255)|MUL|是|角色id|
