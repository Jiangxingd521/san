# 仓库信息(t_ser_warehouse_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|warehouse_name|varchar(255)||是|仓库名称|
|user_id|bigint(20)|MUL|是|系统用户id|
|warehouse_person|varchar(255)||是|仓库管理员|
|warehouse_person_mobile|varchar(255)||是|仓库管理员电话|
|total_inventory|varchar(255)||是|库存总量|
|warehouse_remark|varchar(255)||是|仓库备注|
|warehouse_state|int(255)||是|仓库状态（0：未使用，1：使用）|
|longitude|double||是|经度|
|latitude|double||是|维度|
|is_inventory|int(255)||是|清点库存（0：不清点，1：清点）|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
