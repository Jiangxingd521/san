# 导航栏信息表(t_sys_navigation_bar_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(255)|PRI|否||
|bar_name|varchar(255)||是|导航栏名称|
|bar_path|varchar(255)||是|导航栏链接|
|bar_icon|varchar(255)||是|导航栏图标|
|bar_sort|int(255)||是|导航栏顺序|
|remark|varchar(255)||是|备注信息|
|bar_state|int(255)||是|状态（0：有效，1：无效）|
|create_time|timestamp||是|创建时间|
|update_time|timestamp||是|修改时间|
