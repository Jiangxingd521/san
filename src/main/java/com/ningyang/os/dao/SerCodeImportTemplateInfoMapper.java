package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.output.vo.web.base.CodeImportTemplateVo;
import com.ningyang.os.pojo.SerCodeImportTemplateInfo;

import java.util.List;


/**
 * <p>
 * 码导入模板 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-12
 */
public interface SerCodeImportTemplateInfoMapper extends BaseMapper<SerCodeImportTemplateInfo> {

    List<CodeImportTemplateVo> selectCodeImportVoByCondition(QueryCodeCondition condition);

    List<CodeImportTemplateVo> selectCodeImportVoPageByCondition(QueryCodeCondition condition);

    Long selectCodeImportVoPageCountByCondition(QueryCodeCondition condition);

    CodeImportTemplateVo selectCodeImportTemplateVo(Long templateId);
}
