# 退货订单商品明细(t_ser_purchase_order_info_details)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|purchase_id|bigint(20)||是|退货订单id|
|product_id|bigint(20)||是|产品系列id|
|box_number|int(255)||是|箱数量|
|is_no|int(11)||是|是否直接入库（0：是，1：否）|
|purchase_type|int(255)||是|退货发起（0：供应商发起，1：系统用户发起）|
|purchase_state|int(255)||是|退货状态（0：未完成，1：已完成）|
|user_id|bigint(20)||是|操作人|
|idata1|int(255)||是||
|idata2|int(255)||是||
|idata3|int(255)||是||
|idata4|int(255)||是||
|sdata1|varchar(255)||是||
|sdata2|varchar(255)||是||
|sdata3|varchar(255)||是||
|sdata4|varchar(255)||是||
|create_time|datetime||是||
|update_time|datetime||是||
