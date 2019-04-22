package com.ningyang.os.controller.system.file;

import com.ningyang.os.action.output.dto.web.FileUploadDto;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.service.ISysFileInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;

import static com.ningyang.os.action.utils.UuidUtil.generateUUID;

/**
 * @Author： kaider
 * @Date：2018/10/18 11:34
 * @描述：系统文件上传
 */
@RestController
@RequestMapping("sys/file")
public class FileUploadController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private ISysFileInfoService fileInfoService;


    @PostMapping("upload")
    public Map<String, Object> fileUpload(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request
    ) {
        try {
            if (!file.isEmpty()) {
                String saveFileName = file.getOriginalFilename();
                String fileName = generateUUID() + saveFileName;
                File saveFile = new File(request.getSession().getServletContext().getRealPath("/upload/") + fileName);
                String saveFilePath = "upload/" + fileName;
                if (!saveFile.getParentFile().exists()) {
                    saveFile.getParentFile().mkdirs();
                }
                try {
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    FileUploadDto fileDto = fileInfoService.add(saveFilePath, saveFile.getName());
                    return WebResult.success().put("fileInfo", fileDto).toMap();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return WebResult.failure("上传失败," + e.getMessage()).toMap();
                } catch (IOException e) {
                    e.printStackTrace();
                    return WebResult.failure("上传失败," + e.getMessage()).toMap();
                }
            } else {
                return WebResult.failure("上传失败，文件为空").toMap();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(e).toMap();
        }
    }

}
