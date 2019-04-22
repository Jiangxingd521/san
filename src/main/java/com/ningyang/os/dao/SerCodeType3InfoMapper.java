package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.output.vo.web.base.CodeTypeVo;
import com.ningyang.os.pojo.SerCodeType3Info;

import java.util.List;

/**
 * <p>
 * 溯源码类型 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
public interface SerCodeType3InfoMapper extends BaseMapper<SerCodeType3Info> {

    List<CodeTypeVo> selectCodeTypeVoByCondition(QueryCodeCondition condition);


}
