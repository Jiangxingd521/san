# 角色关联菜单(t_sys_role_menu_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(255)|PRI|否||
|role_id|bigint(255)|MUL|是|角色id|
|menu_id|bigint(255)|MUL|是|菜单id|
