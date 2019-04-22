package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.serve.ImportCodeCommand;
import com.ningyang.os.action.input.condition.serve.QueryApplyCodeCondition;
import com.ningyang.os.action.output.vo.web.serve.ImportCodeVo;
import com.ningyang.os.dao.LCodeImportFileInfoMapper;
import com.ningyang.os.pojo.LCodeImportFileInfo;
import com.ningyang.os.service.ILCodeImportFileInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.DateUtil.getOrderNum;
import static com.ningyang.os.action.utils.DateUtil.timeToStr;

/**
 * <p>
 * 溯源码文件上传记录 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-11-22
 */
@Service
public class LCodeImportFileInfoServiceImpl extends ServiceImpl<LCodeImportFileInfoMapper, LCodeImportFileInfo> implements ILCodeImportFileInfoService {


    @Override
    public Page<ImportCodeVo> findImportCodeVoPageByCondition(QueryApplyCodeCondition condition) {
        Page<ImportCodeVo> pageVo = new Page<>();
        List<ImportCodeVo> listVoTemp = baseMapper.selectImportCodeVoPageByCondition(condition);
        long total = baseMapper.selectImportCodeVoPageCountByCondition(condition);
        for (ImportCodeVo vo : listVoTemp) {
            vo.setCreateTimeStr(timeToStr(vo.getCreateTime()));
        }
        pageVo.setRecords(listVoTemp);
        pageVo.setTotal(total);
        pageVo.setSize(condition.getPage());
        pageVo.setCurrent(condition.getLimit());
        return pageVo;
    }

    @Override
    public boolean add(ImportCodeCommand command) {
        LCodeImportFileInfo info = new LCodeImportFileInfo();
        info.setFileName(command.getImportFileName());
        info.setFilePath(command.getSaveFilePath());
        info.setCodeCount(command.getCodeCount());
        info.setTemplateId(command.getTemplateId());
        info.setUploadOrder(getOrderNum());
        info.setUserId(command.getUserId());
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        return save(info);
    }


}
