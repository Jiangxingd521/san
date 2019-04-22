package com.ningyang.os.controller.center;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.config.SystemConfig;
import com.ningyang.os.action.input.command.web.serve.ApplyCodeCommand;
import com.ningyang.os.action.input.command.web.serve.CenterCodeCommand;
import com.ningyang.os.action.input.condition.base.QueryCodeCondition;
import com.ningyang.os.action.input.condition.serve.QueryApplyCodeCondition;
import com.ningyang.os.action.output.vo.web.serve.ApplyCodeVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.pojo.SerApplyCodeTemplate;
import com.ningyang.os.pojo.SysUserInfo;
import com.ningyang.os.service.ISerApplyCodeInfoService;
import com.ningyang.os.service.ISerApplyCodeTableInfoService;
import com.ningyang.os.service.ISerApplyCodeTemplateService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.io.FileUtil.exist;
import static cn.hutool.core.io.FileUtil.mkdir;
import static cn.hutool.core.util.ZipUtil.zip;
import static cn.hutool.http.HttpUtil.post;
import static com.ningyang.os.action.enums.SystemErrorEnum.*;
import static com.ningyang.os.action.utils.DateUtil.getOrderNum;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.codec.i

/**
 * @Author： kaider
 * @Date：2018/11/15 16:15
 * @描述：溯源码申请
 */
@RestController
@RequestMapping("center/code/apply")
public class CodeApplyController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeApplyController.class);

    @Autowired
    private ISerApplyCodeInfoService infoService;
    @Autowired
    private ISerApplyCodeTemplateService templateService;
    @Autowired
    private ISerApplyCodeTableInfoService tableInfoService;
    @Autowired
    private SystemConfig config;

    @GetMapping("getCodeApplyPage")
    public Map<String, Object> getCodeApplyPage(
            QueryCodeCondition condition
    ) {
        try {
            Page<ApplyCodeVo> pageVo = infoService.findApplyCodeVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    /**
     * 向中心请求二维码
     *
     * @param userToken
     * @param command
     * @return
     */
    @PostMapping("add")
    public Map<String, Object> add(
            @RequestHeader("Authorization") String userToken,
            @RequestBody ApplyCodeCommand command
    ) {
        try {
            SysUserInfo userInfo = getBaseUserInfo(userToken);
            if (userInfo != null) {
                command.setApplyUserId(userInfo.getId());

                String userCode = getAuthorizationCode(0);
                if (isNotBlank(userCode)) {
                    command.setUserCode(userCode);
                    if (command.getCodeType() == 1) {
                        //条形码
                        command.setCodePosition(2L);
                    }
                    //设定内外码
                    if (command.getCodePositionType() == 1) {
                        command.setCodePosition(1L);
                    } else {
                        command.setCodePosition(2L);
                    }
                    command.setCodeOrder(getOrderNum());
                    String result = post(config.getDefaultCenterUrl() + "company/web/api/makeCodeOrder", JSONObject.toJSONString(command));
                    JSONObject object = JSONObject.parseObject(result);
                    boolean flag = (boolean) object.get("result");
                    if (flag) {
                        //插入申请记录
                        boolean flag1 = infoService.add(command);
                        if (flag1) {
                            return WebResult.success().toMap();
                        } else {
                            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
                        }
                    }
                } else {
                    return WebResult.failure(PERMISSION_ERROR.getInfo()).toMap();
                }
            }
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }

    /**
     * 中心通信存储溯源码
     *
     * @param command
     * @return
     */
    @PostMapping("getApplyCodeData")
    public Map<String, Object> getApplyCodeData(
            @RequestBody CenterCodeCommand command
    ) {
        String centerCode = command.getApiCode();
        String nodeCode = getAuthorizationCode(1);
//        System.out.println(JSONObject.toJSONString(command));
        if (centerCode.equals(nodeCode)) {
            boolean codeFlag = templateService.addBatch(command);
            if (codeFlag) {
                boolean applyFlag = infoService.updateApplyState(command);
                if (applyFlag) {
                    tableInfoService.addCodeFlag(command.getCodeFlag());
                    return WebResult.success().toMap();
                }
                return WebResult.failure(EDIT_ERROR.getInfo()).toMap();
            }
            return WebResult.failure(IMPORT_DATA_ERROR.getInfo()).toMap();
        } else {
            return WebResult.failure(SEND_ERROR.getInfo()).toMap();
        }
    }


    /**
     * 生成溯源码压缩包
     *
     * @param condition
     * @return
     */
    @GetMapping("downCode")
    public Map<String, Object> downCode(
            QueryApplyCodeCondition condition
    ) {
        try {
            String rootFilePath = config.getDefaultQRCodeTemplateFilePath();
            String zipFileName = condition.getCodeOrder();

            boolean flag = exist(rootFilePath + "/" + zipFileName + ".zip");
            if (!flag) {
                //不存在
                // 文件名(同时也是需要zip的文件)
                File pFile = mkdir(rootFilePath + zipFileName);
                //创建父文件夹
                String pFilePath = pFile.getPath();
                int codeType = condition.getCodeType();

                List<SerApplyCodeTemplate> codeList = templateService.findCodeVoList(condition);

                BufferedWriter bw;
                if (codeType == 2) {
                    //二维码
                    bw = new BufferedWriter(new FileWriter(pFilePath + "/" +zipFileName
                            +'-'+condition.getCodeTypeName()+'-'+condition.getCodePositionTypeName()+".txt"));
                    for (SerApplyCodeTemplate code : codeList) {
//                        encode(code.getCodeContent(), pFilePath, false, String.valueOf(code.getCenterId()));
                        bw.write(code.getCodeContent() + "\r");
                        bw.newLine();
                        bw.flush();
                    }
                    bw.close();
                } else if (codeType == 1) {
                    //条形码
                    bw = new BufferedWriter(new FileWriter(pFilePath + "/" + zipFileName
                            +'-'+condition.getCodeTypeName()+'-'+condition.getCodePositionTypeName()+ ".txt"));
                    for (SerApplyCodeTemplate code : codeList) {
//                        generateFile(code.getCodeContent(), pFilePath + "/" + code.getCenterId() + ".png");
                        bw.write(code.getCodeContent() + "\r");
                        bw.newLine();
                        bw.flush();
                    }
                    bw.close();
                }
                //创建压缩文件
                zip(pFile);
            }
            return WebResult.success().put("zipFileName", zipFileName).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

    /**
     * 下载溯源码压缩包
     *
     * @param condition
     * @return
     * @throws IOException
     */
    @GetMapping("downloadZip")
    public ResponseEntity<byte[]> downloadZip(QueryApplyCodeCondition condition) throws IOException {
        //下载具体文件
        File file = new File(config.getDefaultQRCodeTemplateFilePath() + condition.getCodeOrder() + ".zip");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}
