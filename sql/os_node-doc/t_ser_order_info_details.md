# 销售订单详情(t_ser_order_info_details)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|order_id|bigint(255)|MUL|是|订单id|
|product_id|bigint(255)||是|产品id|
|box_number|int(255)||是|箱数|
|user_id|bigint(255)||是|操作人|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
