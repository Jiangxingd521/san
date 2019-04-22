# 商品入库(t_ser_warehouse_goods_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|source_type|int(255)||是|入库来源（0：生产入库，1：换货入库，2：退货入库，3：换仓入库）|
|warehouse_id|bigint(20)|MUL|是|仓库id|
|goods_id|bigint(20)|MUL|是|商品id|
|box_no|varchar(255)||是|箱码|
|goods_state|int(255)||是|商品状态（0：未入库，1：入库，2：出库，3：丢失，4：已扫描兑奖）|
|warehouse_in_no|varchar(20)||是|入库编号|
|user_id|bigint(20)||是|入库人|
|remark|varchar(255)||是|入库备注|
|create_time|datetime||是|入库时间|
|update_time|datetime||是|修改时间|
