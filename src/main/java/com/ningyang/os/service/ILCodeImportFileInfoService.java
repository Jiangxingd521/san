package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.ImportCodeCommand;
import com.ningyang.os.action.input.condition.serve.QueryApplyCodeCondition;
import com.ningyang.os.action.output.vo.web.serve.ImportCodeVo;
import com.ningyang.os.pojo.LCodeImportFileInfo;

/**
 * <p>
 * 溯源码文件上传记录 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-22
 */
public interface ILCodeImportFileInfoService extends IService<LCodeImportFileInfo> {

    Page<ImportCodeVo> findImportCodeVoPageByCondition(QueryApplyCodeCondition condition);

    boolean add(ImportCodeCommand command);

}
