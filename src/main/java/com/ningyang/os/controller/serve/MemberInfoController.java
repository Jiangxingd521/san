package com.ningyang.os.controller.serve;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberInfoVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.service.IMemberInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.DATA_ERROR;

/**
 * @Author： kaider
 * @Date：2018/12/06 09:49
 * @描述：会员信息
 */
@RestController
@RequestMapping("serve/member/info")
public class MemberInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberInfoController.class);

    @Autowired
    private IMemberInfoService infoService;

    @GetMapping("getPageList")
    public Map<String, Object> getPageList(
            QueryMemberCondition condition
    ) {
        try {
            Page<MemberInfoVo> pageVo = infoService.findMemberInfoVoPageByCondition(condition);
            return WebResult.success().put("pageVo", pageVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }


}
