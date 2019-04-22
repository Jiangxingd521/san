package com.ningyang.os.controller.serve;

import com.ningyang.os.action.input.command.web.serve.MemberPointRuleCommand;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberPointRuleVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.service.IMemberPointRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.DATA_ERROR;
import static com.ningyang.os.action.enums.SystemErrorEnum.OPERATING_ERROR;

/**
 * @Author： kaider
 * @Date：2018/11/30 12:55
 * @描述：会员积分规则
 */
@RestController
@RequestMapping("serve/member/pointRule")
public class MemberPointRuleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberPointRuleController.class);

    @Autowired
    private IMemberPointRuleService infoService;

    @GetMapping("getList")
    public Map<String, Object> getList(
            QueryMemberCondition condition
    ) {
        try {

            List<MemberPointRuleVo> listVo = infoService.findMemberPointRuleVoListByCondition(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }

    @GetMapping("getListfindName")
    public Map<String, Object> getListfindName(String ruleName,Long ruleID) {
        try {
            System.out.println(ruleID+"-----------");
            if (!infoService.getListfindName(ruleName,ruleID)) {
                return WebResult.success().toMap();
            }
            return WebResult.failure("用户名已存在").toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }
    @DeleteMapping("deletePointRuleById/{RuleId}")
    public Map<String, Object> deletePointRuleById(
            @PathVariable("RuleId") Long RuleId
    ) {
        try {
            boolean flag = infoService.deletePointRuleById(RuleId);
            if (flag) {
                return WebResult.success().toMap();
            }
            return WebResult.failure("").toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure("").toMap();
        }
    }
    @PostMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate(
            @RequestHeader("Authorization") String userToken,
            @RequestBody MemberPointRuleCommand command
    ) {
        try {
            Long operateUserId = getBaseUserInfo(userToken).getId();
            boolean flag = infoService.addOrUpdate(command, operateUserId);
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
