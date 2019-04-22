package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.output.vo.web.base.CodeTypeVo;
import com.ningyang.os.pojo.SerCodeType1Info;

import java.util.List;

/**
 * <p>
 * 内外码 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
public interface ISerCodeType1InfoService extends IService<SerCodeType1Info> {

    List<CodeTypeVo> findCodeTypeVoByCondition();

}
