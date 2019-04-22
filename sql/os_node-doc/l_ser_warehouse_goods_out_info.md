# 商品出库记录日志(l_ser_warehouse_goods_out_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|order_id|bigint(20)||是|销售订单|
|warehouse_id|bigint(20)||是|仓库id|
|box_no|varchar(255)||是|箱码|
|product_id|bigint(20)||是|产品id|
|dealer_id|bigint(20)||是|经销商id|
|user_id|bigint(20)||是|操作人|
|goods_out_time|datetime||是|出库时间|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
