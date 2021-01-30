package cn.hll520.linling.core.service;

import cn.hll520.linling.core.autovalue.AppDefaultHostValue;
import cn.hll520.linling.core.autovalue.AppInfoValue;
import cn.hll520.linling.core.autovalue.AppManageValue;
import cn.hll520.linling.core.object.Menu;
import cn.hll520.linling.core.object.MenuGroup;

import java.util.List;

/**
 * 描述： 管理后台相关设置
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-30-17:38
 * @since 2021-01-30-17:38
 */
public interface IManageServer {

    /**
     * 添加左边的面板
     *
     * @param menuGroups 左边菜单组
     * @return 菜单组
     */
    List<MenuGroup> leftMenu(List<MenuGroup> menuGroups);

    /**
     * 头部菜单组
     *
     * @param menus 头部菜单
     * @return 菜单
     */
    List<Menu> topMenu(List<Menu> menus);

    /**
     * 默认欢迎页面url
     *
     * @param menuGroups 右边按钮组
     * @param welcome    配置文件中的欢迎页
     * @return 实际欢迎页url
     */
    default String welcome(List<MenuGroup> menuGroups, String welcome) {
        if (welcome != null) {
            return welcome;
        }
        if (menuGroups == null
                || menuGroups.size() < 1
                || menuGroups.get(0) == null
                || menuGroups.get(0).getMenus() == null
                || menuGroups.get(0).getMenus().size() < 1
                || menuGroups.get(0).getMenus().get(0).getUrl() == null) {
            return AppDefaultHostValue.appInfoHost;
        }
        return menuGroups.get(0).getMenus().get(0).getUrl();
    }

    /**
     * 修改manage 管理 默认空实现
     *
     * @param manageConfig 管理配置
     * @param infoValue    app info
     * @param defaultMenus 默认的菜单组
     */
    default void modify(AppManageValue manageConfig, AppInfoValue infoValue, List<Menu> defaultMenus) {
        if (manageConfig.getTitle() == null) {
            manageConfig.setTitle(infoValue.getAppName() + "控制台");
        }
        if (manageConfig.getTop() == null) {
            manageConfig.setTop(new Menu(manageConfig.getTitle(), infoValue.getAppHost()));
        }
        if (manageConfig.getNavbar() == null) {
            manageConfig.setNavbar(defaultMenus);
        }
    }
}
