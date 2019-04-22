package com.ningyang.os.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ningyang.os.action.input.condition.serve.QueryApplyCodeCondition;
import com.ningyang.os.action.output.vo.web.serve.ImportCodeVo;
import com.ningyang.os.pojo.LCodeImportFileInfo;

import java.util.List;

/**
 * <p>
 * 溯源码文件上传记录 Mapper 接口
 * </p>
 *
 * @author kaider
 * @since 2018-11-22
 */
public interface LCodeImportFileInfoMapper extends BaseMapper<LCodeImportFileInfo> {

    List<ImportCodeVo> selectImportCodeVoPageByCondition(QueryApplyCodeCondition condition);

    Long selectImportCodeVoPageCountByCondition(QueryApplyCodeCondition condition);

}
