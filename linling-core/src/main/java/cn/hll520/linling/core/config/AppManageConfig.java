package cn.hll520.linling.core.config;

import cn.hll520.linling.core.autovalue.AppHostValue;
import cn.hll520.linling.core.autovalue.AppInfoValue;
import cn.hll520.linling.core.autovalue.AppManageValue;
import cn.hll520.linling.core.object.Menu;
import cn.hll520.linling.core.object.MenuGroup;
import cn.hll520.linling.core.service.IManageServer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述： 修正后台管理
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-21-16:14
 * @since 2021-01-21-16:14
 */
@Component
public class AppManageConfig {
    private final AppManageValue value;
    private final AppInfoValue infoValue;
    private final AppHostValue hostValue;
    private final IManageServer manageServer;

    public AppManageConfig(AppManageValue value, AppInfoValue infoValue,
                           AppHostValue hostValue, IManageServer manageServer) {
        this.value = value;
        this.infoValue = infoValue;
        this.hostValue = hostValue;
        this.manageServer = manageServer;
    }

    /**
     * 获取默认的系统管理后台配置
     *
     * @return 后台配置
     */
    public AppManageValue getManage() {
        // 设置头部导航
        List<Menu> navbar = value.getNavbar() == null ? defaultMenus() : value.getNavbar();
        value.setNavbar(manageServer.topMenu(navbar));
        // 右边导航
        List<MenuGroup> menuGroups = value.getMenu() == null ? defaultGroupMenus() : value.getMenu();
        value.setMenu(manageServer.leftMenu(menuGroups));
        // 设置欢迎页
        value.setWelcome(manageServer.welcome(value.getMenu(), value.getWelcome()));

        // null 转换为 框架窗口
        for (Menu menu : value.getNavbar()) {
            if (menu.getTarget() == null) {
                menu.setTarget("_view");
            }
        }
        for (MenuGroup menuGroup : value.getMenu()) {
            if (menuGroup != null) {
                for (Menu menu : menuGroup.getMenus()) {
                    if (menu.getTarget() == null) {
                        menu.setTarget("_view");
                    }
                }
            }
        }

        // 整体修改
        manageServer.modify(value, infoValue, defaultMenus());
        return value;
    }

    /**
     * 默认菜单
     *
     * @return 默认菜单列表
     */
    private List<Menu> defaultMenus() {
        List<Menu> defaultMenus = new ArrayList<>();
        defaultMenus.add(new Menu("关于应用", hostValue.getAppInfoHost()));
        defaultMenus.add(new Menu("API接口", hostValue.getAppApiHost()));
        defaultMenus.add(new Menu("SQL监控", hostValue.getAppSQLManageHost()));
        return defaultMenus;
    }

    /**
     * 默认菜单组
     *
     * @return 默认菜单组
     */
    private List<MenuGroup> defaultGroupMenus() {
        ArrayList<MenuGroup> menuGroups = new ArrayList<>();
        MenuGroup menuGroup = new MenuGroup();
        menuGroup.setName("defaultMenu");
        menuGroup.setMenus(defaultMenus());
        menuGroups.add(menuGroup);
        value.setMenu(menuGroups);
        return menuGroups;
    }
}
