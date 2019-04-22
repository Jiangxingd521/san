# 经销商信息(t_ser_dealer_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|dealer_name|varchar(255)||是|经销商名称|
|person_name|varchar(255)||是|联系人|
|person_mobile|varchar(255)||是|联系人电话|
|dealer_state|int(255)||是|与供应商合作状态（0：合作，1：不合作）|
|region_id|varchar(255)||是|供应商区域id|
|dealer_address|varchar(255)||是|供应商详细地址|
|social_code|varchar(255)||是|供应商社会码|
|dealer_remark|varchar(255)||是|经销商备注|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
