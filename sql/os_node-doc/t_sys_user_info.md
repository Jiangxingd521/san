# 用户信息表(t_sys_user_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(255)|PRI|否||
|pid|bigint(255)||是|父id|
|user_name|varchar(255)||是|用户昵称|
|login_name|varchar(255)||是|登录名|
|login_password|varchar(255)||是|登录密码|
|login_password_plaintext|varchar(255)||是|密码明文(可设置可不设置)|
|user_state|int(255)||是|用户状态（0：有效，1：无效，2：禁用）|
|user_type|int(255)||是|用户类型（0：系统用户，1：其他）|
|authorization_code|varchar(255)||是|授权码|
|create_user_id|bigint(255)||是|创建人|
|create_time|timestamp||是|创建时间|
|update_time|timestamp||是|修改时间|
