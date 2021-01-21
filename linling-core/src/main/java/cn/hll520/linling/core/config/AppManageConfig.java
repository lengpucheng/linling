package cn.hll520.linling.core.config;

import cn.hll520.linling.core.config.value.AppInfoValue;
import cn.hll520.linling.core.config.value.AppManageValue;
import cn.hll520.linling.core.object.Menu;
import cn.hll520.linling.core.object.MenuGroup;
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

    public AppManageConfig(AppManageValue value, AppInfoValue infoValue) {
        this.value = value;
        this.infoValue = infoValue;
    }

    /**
     * 获取默认的系统管理后台配置
     *
     * @return 后台配置
     */
    public AppManageValue getManage() {
        if (value.getTitle() == null) {
            value.setTitle(infoValue.getAppName() + "控制台");
        }
        if (value.getTop() == null) {
            value.setTop(new Menu(value.getTitle(), infoValue.getAppHost()));
        }
        if (value.getNavbar() == null) {
            value.setNavbar(defaultMenus());
        }
        if (value.getMenu() == null) {
            List<MenuGroup> menuGroups = new ArrayList<>();
            MenuGroup menuGroup = new MenuGroup();
            menuGroup.setName("No1");
            menuGroup.setMenus(defaultMenus());
            menuGroups.add(menuGroup);
            value.setMenu(menuGroups);
        }
        if (value.getWelcome() == null) {
            value.setWelcome(value.getMenu().get(0).getMenus().get(0).getUrl());
        }
        return value;
    }

    /**
     * 默认菜单
     *
     * @return 默认菜单列表
     */
    private List<Menu> defaultMenus() {
        List<Menu> defaultMenus = new ArrayList<>();
        defaultMenus.add(new Menu("关于应用", "/core"));
        defaultMenus.add(new Menu("API接口", infoValue.getAppApiHost()));
        defaultMenus.add(new Menu("SQL监控", infoValue.getAppSQLManageHost()));
        return defaultMenus;
    }
}
