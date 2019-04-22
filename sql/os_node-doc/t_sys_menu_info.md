# 菜单信息(t_sys_menu_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(255)|PRI|否||
|pid|bigint(255)||是|父id|
|menu_name|varchar(255)||是|菜单名称|
|menu_type|int(255)||是|类型（0：菜单，1：按钮）|
|menu_icon|varchar(255)||是|图标链接|
|menu_path|varchar(255)||是|菜单链接|
|menu_permission|varchar(255)||是|菜单许可标识|
|menu_sort|int(255)||是|排序|
|menu_state|int(255)||是|状态（0：有效，1：无效）|
|menu_remark|varchar(255)||是|描述|
|create_time|timestamp||是|创建时间|
|update_time|timestamp||是|修改时间|
