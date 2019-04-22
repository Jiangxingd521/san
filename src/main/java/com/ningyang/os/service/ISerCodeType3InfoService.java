package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.base.CodeTypeCommand;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.output.vo.web.base.CodeTypeVo;
import com.ningyang.os.pojo.SerCodeType3Info;

import java.util.List;

/**
 * <p>
 * 溯源码类型 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
public interface ISerCodeType3InfoService extends IService<SerCodeType3Info> {

    List<CodeTypeVo> findCodeTypeVoByCondition(QueryCodeCondition condition);

    boolean addOrUpdate(CodeTypeCommand command);

}
