# 商品退货订单(t_ser_purchase_order_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|dealer_id|bigint(255)||是|经销商id|
|order_no|varchar(255)||是|退货订单号|
|order_remark|varchar(255)||是|退货原因|
|order_state|int(255)||是|订单状态（0：备单，1：确认订单，2：待收货，3：收货未完成，4：已完成）|
|product_number|varchar(255)||是|数量|
|financial_id|bigint(20)||是|财务|
|user_id|bigint(20)||是|创建人|
|idata1|int(255)||是||
|idata2|int(255)||是||
|idata3|int(255)||是||
|idata4|int(255)||是||
|sdata1|varchar(255)||是||
|sdata2|varchar(255)||是||
|sdata3|varchar(255)||是||
|sdata4|varchar(255)||是||
|create_time|datetime||是|创建时间|
|update_time|datetime||是|修改时间|
