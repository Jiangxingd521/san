# 企业api标识(t_sys_api_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|api_type|int(255)||是|api标识类型(0：用户授权码，1：接口授权码)|
|api_code|varchar(255)||是|api标识内容|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
