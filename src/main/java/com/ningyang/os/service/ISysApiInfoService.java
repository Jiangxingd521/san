package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.input.command.web.serve.RegisterCodeCommand;
import com.ningyang.os.pojo.SysApiInfo;

/**
 * <p>
 * 企业api标识 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-19
 */
public interface ISysApiInfoService extends IService<SysApiInfo> {

    boolean registerCode(RegisterCodeCommand command);

    boolean checkCode();

}
