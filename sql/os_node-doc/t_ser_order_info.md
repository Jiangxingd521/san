# 销售订单表(t_ser_order_info)
| 列名   | 类型   | KEY  | 可否为空 | 注释   |
| ---- | ---- | ---- | ---- | ---- |
|id|bigint(20)|PRI|否||
|order_no|varchar(255)||是|订单号|
|dealer_id|bigint(20)||是|经销商|
|dealer_code|varchar(255)||是|经销商社会编码|
|brand_id|bigint(11)||是|品牌|
|series_id|bigint(255)||是|产品|
|product_id|bigint(255)||是|系列|
|product_number|varchar(255)||是|数量|
|order_state|int(11)||是|订单状态（0：备单，1：确认订单，2：发货）|
|order_remark|varchar(1024)||是|备注|
|user_id|bigint(20)||是|创建人|
|financial_id|bigint(20)||是|财务|
|iData1|int(11)||是||
|iData2|int(11)||是||
|iData3|int(11)||是||
|iData4|int(11)||是||
|sData1|varchar(255)||是||
|sData2|varchar(255)||是||
|sData3|varchar(255)||是||
|sData4|varchar(255)||是||
|create_time|datetime||是||
|update_time|datetime||是||
