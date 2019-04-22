# 溯源码文件上传记录(l_code_import_file_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|file_name|varchar(255)||是|上传的文件名|
|file_path|varchar(255)||是|文件存放路径|
|upload_order|varchar(255)||是|上传的订单|
|user_id|bigint(20)||是|操作人|
|code_count|bigint(255)||是|溯源码个数|
|template_id|bigint(20)||是|模板id|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
