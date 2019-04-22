package com.ningyang.os.controller.center;

import com.ningyang.os.action.input.command.web.serve.RegisterCodeCommand;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.ISysApiInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.OPERATING_ERROR;

/**
 * @Author： kaider
 * @Date：2018/11/19 14:30
 * @描述：获取注册码
 */
@RestController
@RequestMapping("center")
public class RegisterCodeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCodeController.class);

    @Autowired
    private ISysApiInfoService infoService;

    @PostMapping("registerCode")
    public Map<String, Object> registerCode(
            @RequestBody RegisterCodeCommand command
    ) {
        try {
            boolean flag = infoService.registerCode(command);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }

    /**
     * 校验是否已经获取授权码（true：有，false：没有）
     *
     * @return
     */
    @GetMapping("checkCode")
    public Map<String, Object> checkCode() {
        try {
            boolean flag = infoService.checkCode();
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(OPERATING_ERROR.getInfo()).toMap();
        }
    }

}
