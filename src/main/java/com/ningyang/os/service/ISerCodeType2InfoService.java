package com.ningyang.os.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ningyang.os.action.output.vo.web.base.CodeTypeVo;
import com.ningyang.os.pojo.SerCodeType2Info;

import java.util.List;

/**
 * <p>
 * 条形码二维码 服务类
 * </p>
 *
 * @author kaider
 * @since 2018-11-23
 */
public interface ISerCodeType2InfoService extends IService<SerCodeType2Info> {

    List<CodeTypeVo> findCodeTypeVoByCondition();

}
