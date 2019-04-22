# 商品入库记录日志(l_ser_warehouse_goods_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|source_type|int(255)||是|入库来源（0：生产入库，1：退货入库）|
|warehouse_id|bigint(20)|MUL|是|仓库id|
|purchase_id|bigint(20)||是|退货订单id|
|goods_id|bigint(20)||是|商品id|
|product_id|bigint(20)||是|产品系列id|
|box_no|varchar(255)||是|箱码|
|warehouse_in_no|varchar(20)||是|入库单号|
|user_id|bigint(20)||是|操作人|
|warehouse_in_time|datetime||是|入库时间|
|remark|varchar(255)||是|备注|
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
