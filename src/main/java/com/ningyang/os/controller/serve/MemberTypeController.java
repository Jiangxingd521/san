package com.ningyang.os.controller.serve;

import com.ningyang.os.action.input.command.web.serve.MemberTypeCommand;
import com.ningyang.os.action.input.condition.serve.QueryMemberCondition;
import com.ningyang.os.action.output.vo.web.serve.MemberTypeVo;
import com.ningyang.os.action.utils.WebResult;
import com.ningyang.os.controller.system.BaseController;
import com.ningyang.os.service.IMemberTypeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ningyang.os.action.enums.SystemErrorEnum.DATA_ERROR;
import static com.ningyang.os.action.enums.SystemErrorEnum.OPERATING_ERROR;

/**
 * @Author： kaider
 * @Date：2018/11/30 13:13
 * @描述：会员类型
 */
@RestController
@RequestMapping("serve/member/type")
public class MemberTypeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberTypeController.class);

    @Autowired
    private IMemberTypeInfoService infoService;


    @GetMapping("getList")
    public Map<String, Object> getList(
            QueryMemberCondition condition
    ) {
        try {
            List<MemberTypeVo> listVo = infoService.findMemberTypeVoListByCondition(condition);
            return WebResult.success().put("listVo", listVo).toMap();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure(DATA_ERROR.getInfo()).toMap();
        }
    }
    @DeleteMapping("deleteMemberType/{typeId}")
    public Map<String, Object> deleteMemberType(@PathVariable("typeId")Long typeId) {
        try{
            Boolean flag=infoService.deleteMemberType(typeId);
            if (flag){
                return WebResult.success().toMap();
            }
            return WebResult.failure("").toMap();
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return WebResult.failure("").toMap();
        }
    }
    @PostMapping("addOrUpdate")
    public Map<String, Object> addOrUpdate(
            @RequestHeader("Authorization") String userToken,
            @RequestBody MemberTypeCommand command
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
