# 会员扫码记录(t_member_scanning)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|open_id|varchar(32)||是|微信openid|
|pr_code|varchar(1024)||是|内码|
|idata1|int(11)||是||
|idata2|int(11)||是||
|idata3|int(11)||是||
|idata4|int(11)||是||
|sdata1|varchar(255)||是||
|sdata2|varchar(255)||是||
|sdata3|varchar(255)||是||
|sdata4|varchar(255)||是||
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
