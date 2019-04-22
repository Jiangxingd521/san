package com.ningyang.os.controller.serve;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.command.web.serve.ImportCodeCommand;
import com.ningyang.os.action.input.condition.serve.QueryApplyCodeCondition;
import com.ningyang.os.action.output.vo.web.base.CodeImportTemplateVo;
import com.ningyang.os.action.output.vo.web.serve.ImportCodeVo;
import com.ningyang.os.action.utils.ReadFileBackData;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.pojo.SerApplyCodeTemplate;
import com.ningyang.os.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.DATA_ERROR;
import static com.ningyang.os.action.enums.SystemErrorEnum.IMPORT_DATA_ERROR;
import static com.ningyang.os.action.utils.ReadFileUtil.returnReadFileData;
import static com.ningyang.os.action.utils.UuidUtil.generateUUID;

/**
 * @Author： kaider
 * @Date：2018/11/22 14:15
 * @描述：机器导入溯源码
 */
@RestController
@RequestMapping("center/code")
public class CodeImportController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeImportController.class);

    @Autowired
    private ILCodeImportFileInfoService infoService;
    @Autowired
    private ISerCodeImportTempInfoService tempInfoService;
    @Autowired
    private ISerApplyCodeTableInfoService tableInfoService;
    @Autowired
    private ISerCodeImportTemplateInfoService templateInfoService;
    @Autowired
    private ISerApplyCodeTemplateService codeTemplateService;

    /**
     * @param condition
     * @return
     */
    @GetMapping("getCodeImportPage")
    public Map<String, Object> getCodeImportPage(
            QueryApplyCodeCondition condition
    ) {
        try {
            Page<ImportCodeVo> pageVo = infoService.findImportCodeVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }


    /**
     * 导入机器溯源码文件
     *
     * @param userToken
     * @param file
     * @param templateId
     * @return
     */
    @PostMapping("codeImportFile")
    public Map<String, Object> importFile(
            @RequestHeader("Authorization") String userToken,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Long templateId,
            HttpServletRequest request
    ) {
        if (file == null) {
            return WebResult.failure("请上传文件!").toMap();
        }
        try {
            String fileName = file.getOriginalFilename();
            Long uploadUserId = getBaseUserInfo(userToken).getId();
            //存储上传的文件
            String saveFileName = generateUUID() + fileName;
            File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + saveFileName);
            String saveFilePath = "upload/" + saveFileName;
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
            out.write(file.getBytes());
            out.flush();
            out.close();
            //获取导入文件左右码
            List<ReadFileBackData> fileList = returnReadFileData(file);
//            System.out.println(JSONObject.toJSONString(fileList));
            CodeImportTemplateVo templateVo = templateInfoService.findCodeImportTemplateVo(templateId);
            //溯源码位置
            Long codePosition = templateVo.getLeftCodeType();
            //溯源码位置类型
            Long codePositionType = templateVo.getLeftCodeId();
            //校验左码是否符合
            for (ReadFileBackData data : fileList) {
                String leftCodeFlag = data.getLData().split("/")[7];
                //查询溯源码所在表
                String codeTables = tableInfoService.findCodeTableList(leftCodeFlag);
                //溯源码内容
                String codeContent = data.getLData();
                //溯源码
                SerApplyCodeTemplate code = codeTemplateService.findCodeByTables(codeTables, codeContent);
                if (code.getCodePosition() != codePosition && code.getCodePositionType() != codePositionType) {
                    return WebResult.failure("导入数据与使用模板有误！").toMap();
                }
            }
            //溯源码导入临时表
            boolean codeTempFlag = tempInfoService.add(fileList, templateId);

            if (codeTempFlag) {
                //加入导入日志
                ImportCodeCommand command = new ImportCodeCommand();
                command.setImportFileName(fileName);
                command.setSaveFilePath(saveFilePath);
                command.setCodeCount(Long.valueOf(fileList.size()));
                command.setUserId(uploadUserId);
                command.setTemplateId(templateId);
//                System.out.println(JSONObject.toJSONString(command));
                boolean logFlag = infoService.add(command);
                if (logFlag) {
                    //调用插入码的存储过程
                    tempInfoService.callSetCode();

                    return WebResult.success().toMap();
                }
                return WebResult.failure(IMPORT_DATA_ERROR.getInfo()).toMap();
            }
            return WebResult.failure(IMPORT_DATA_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(IMPORT_DATA_ERROR.getInfo()).toMap();
        }
    }


}
