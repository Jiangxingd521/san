package com.ningyang.os.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ningyang.os.action.input.command.web.base.MenuCommand;
import com.ningyang.os.action.input.condition.base.QueryMenuCondition;
import com.ningyang.os.action.output.vo.web.base.BarMenuVo;
import com.ningyang.os.action.output.vo.web.base.SysMenuVo;
import com.ningyang.os.dao.SysMenuInfoMapper;
import com.ningyang.os.pojo.SysMenuInfo;
import com.ningyang.os.service.ISysMenuInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.ningyang.os.action.utils.TreeUtil.getTreeList;

/**
 * <p>
 * 菜单信息 服务实现类
 * </p>
 *
 * @author kaider
 * @since 2018-09-28
 */
@Service
public class SysMenuInfoServiceImpl extends ServiceImpl<SysMenuInfoMapper, SysMenuInfo> implements ISysMenuInfoService {

    @Override
    public List<BarMenuVo> findUserBarMenuVoList(QueryMenuCondition condition) {
        List<BarMenuVo> listTemp = baseMapper.selectUserBarMenuVoList(condition);
        List<BarMenuVo> menus = getTreeList("0", listTemp);
        return menus;
    }

    @Override
    public List<SysMenuVo> findSysMenuVoList(QueryMenuCondition condition) {
        List<SysMenuVo> listTemp = baseMapper.selectSysMenuVoList(condition);
        List<SysMenuVo> menus;
        if (condition.getMenuType().equals(-1)) {
            menus = getTreeList("0", listTemp);
        } else {
            menus = listTemp;
        }
        return menus;
    }

    @Override
    public boolean add(MenuCommand command) {
        SysMenuInfo menuInfo = new SysMenuInfo();
        menuInfo.setPid(command.getParentId());
        menuInfo.setMenuName(command.getLabel());
        menuInfo.setMenuPermission(command.getCode());
        menuInfo.setMenuType(command.getType());
        menuInfo.setMenuSort(command.getSort());
        menuInfo.setMenuIcon(command.getIcon());
        menuInfo.setMenuPath(command.getPath());
        menuInfo.setMenuState(0);
        menuInfo.setMenuRemark(command.getRemark());
        menuInfo.setCreateTime(new Date());
        menuInfo.setUpdateTime(new Date());
        return save(menuInfo);
    }

    @Override
    public boolean edit(MenuCommand command) {
        SysMenuInfo menuInfo = getById(command.getId());
        menuInfo.setPid(command.getParentId());
        menuInfo.setMenuName(command.getLabel());
        menuInfo.setMenuPermission(command.getCode());
        menuInfo.setMenuType(command.getType());
        menuInfo.setMenuSort(command.getSort());
        menuInfo.setMenuIcon(command.getIcon());
        menuInfo.setMenuPath(command.getPath());
        menuInfo.setMenuState(command.getMenuState());
        menuInfo.setMenuRemark(command.getRemark());
        menuInfo.setUpdateTime(new Date());
        return updateById(menuInfo);
    }

    @Override
    public boolean delete(Long menuId) {
        SysMenuInfo menuInfo = getById(menuId);
        menuInfo.setMenuState(1);
        menuInfo.setUpdateTime(new Date());
        return updateById(menuInfo);
    }
}
