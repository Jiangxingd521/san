package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.OrderDetailsCommand;
import com.ningyang.os.action.input.condition.serve.QueryOrderCondition;
import com.ningyang.os.action.output.vo.web.serve.OrderDetailVo;
import com.ningyang.os.dao.SerPurchaseOrderInfoDetailsMapper;
import com.ningyang.os.pojo.SerPurchaseOrderInfoDetails;
import com.ningyang.os.service.ISerPurchaseOrderInfoDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 退货订单商品明细 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-29
 */
@Service
public class SerPurchaseOrderInfoDetailsServiceImpl extends ServiceImpl<SerPurchaseOrderInfoDetailsMapper, SerPurchaseOrderInfoDetails> implements ISerPurchaseOrderInfoDetailsService {

    @Override
    public List<OrderDetailVo> findOrderDetailVoList(QueryOrderCondition condition) {
        List<OrderDetailVo> listTemp = baseMapper.selectOrderDetailVoList(condition);
        for(OrderDetailVo vo : listTemp){
            condition.setProductId(vo.getProductId());
            int boxNumber = baseMapper.selectOrderDetailBoxCount(condition);
            vo.setBoxNumber(boxNumber);
        }
        return listTemp;
    }

    @Override
    public boolean add(OrderDetailsCommand command, Long operateUserId) {
        SerPurchaseOrderInfoDetails details = new SerPurchaseOrderInfoDetails();
        details.setPurchaseId(command.getPurchaseId());
        details.setProductId(command.getProductId());
        details.setBoxNumber(command.getBoxNumber());
        details.setUserId(operateUserId);
        details.setCreateTime(new Date());
        details.setUpdateTime(new Date());
        return save(details);
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
        return baseMapper.deletePurchaseOrderByNull();
    }
}
