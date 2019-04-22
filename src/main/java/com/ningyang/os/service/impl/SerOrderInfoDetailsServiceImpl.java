package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.OrderDetailsCommand;
import com.ningyang.os.action.input.condition.serve.QueryGoodsPutCondition;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.OrderDetailVo;
import com.ningyang.os.dao.SerOrderInfoDetailsMapper;
import com.ningyang.os.pojo.LSerWarehouseGoodsOutInfo;
import com.ningyang.os.pojo.SerOrderInfoDetails;
import com.ningyang.os.service.ILSerWarehouseGoodsOutInfoService;
import com.ningyang.os.service.ISerOrderInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 销售订单详情 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-26
 */
@Service
public class SerOrderInfoDetailsServiceImpl extends ServiceImpl<SerOrderInfoDetailsMapper, SerOrderInfoDetails> implements ISerOrderInfoDetailsService {

    @Autowired
    private ILSerWarehouseGoodsOutInfoService outInfoService;


    @Override
    public boolean add(OrderDetailsCommand command, Long operateUserId) {
        SerOrderInfoDetails details = new SerOrderInfoDetails();
        details.setProductId(command.getProductId());
        details.setBoxNumber(command.getBoxNumber());
        details.setUserId(operateUserId);
        details.setOrderId(command.getOrderId());
        details.setCreateTime(new Date());
        details.setUpdateTime(new Date());
        return save(details);
    }

    @Override
    public List<OrderDetailVo> findOrderDetailVoList(QueryOrderCondition condition) {
        List<OrderDetailVo> listTemp = baseMapper.selectOrderDetailVoList(condition);
        for(OrderDetailVo vo : listTemp){
            condition.setProductId(vo.getProductId());
            int boxNumber = baseMapper.selectOrderDetailBoxCount(condition);
            vo.setBoxNumber(boxNumber);
            QueryGoodsPutCondition outCondition = new QueryGoodsPutCondition();
            outCondition.setOrderId(vo.getOrderId());
            outCondition.setProductId(vo.getProductId());
            int outBoxCountByProduct = getOrderSaleBoxCount(outCondition);
            if(boxNumber == outBoxCountByProduct){
                vo.setSaleState(1);
            }else{
                vo.setSaleState(0);
            }
        }
        return listTemp;
    }

    @Override
    public int boxCount(QueryOrderCondition condition) {
        List<OrderDetailVo> listTemp = findOrderDetailVoList(condition);
        int boxCount = 0;
        for (OrderDetailVo vo : listTemp) {
            boxCount = boxCount + vo.getBoxNumber();
        }
        return boxCount;
    }

    @Override
    public boolean delete() {
        return baseMapper.deleteOrderByNull();
    }

    /**
     *
     * @param condition
     * @return
     */
    private int getOrderSaleBoxCount(QueryGoodsPutCondition condition){
        return outInfoService.count(new QueryWrapper<LSerWarehouseGoodsOutInfo>()
                .eq("order_id",condition.getOrderId())
                .eq("product_id",condition.getProductId()));
    }


}
