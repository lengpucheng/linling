package cn.hll520.linling.core.service.impl;

import cn.hll520.linling.core.object.Menu;
import cn.hll520.linling.core.object.MenuGroup;
import cn.hll520.linling.core.service.IManageServer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：管理控制台菜单默认实现类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-30-17:41
 * @since 2021-01-30-17:41
 */
@Service
public class DefaultManageServer implements IManageServer {

    @Override
    public List<MenuGroup> leftMenu(List<MenuGroup> menuGroups) {
        return menuGroups;
    }

    @Override
    public List<Menu> topMenu(List<Menu> menus) {
        return menus;
    }

}
