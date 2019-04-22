package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryWarehouseCondition;
import com.ningyang.os.action.output.vo.web.serve.WarehousePersonVo;
import com.ningyang.os.action.output.vo.web.serve.WarehouseVo;
import com.ningyang.os.pojo.SerWarehouseInfo;

import java.util.List;

/**
 * <p>
 * 仓库信息 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface SerWarehouseInfoMapper extends BaseMapper<SerWarehouseInfo> {

    List<WarehouseVo> selectWarehouseVoPageByCondition(QueryWarehouseCondition condition);

    Long selectWarehouseVoPageCountByCondition(QueryWarehouseCondition condition);

    List<WarehousePersonVo> selectWarehousePersonVoByCondition();

    List<WarehouseVo> selectWarehouseVoListByCondition(QueryWarehouseCondition condition);

}
