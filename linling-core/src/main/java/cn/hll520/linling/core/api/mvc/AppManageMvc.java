package cn.hll520.linling.core.api.mvc;

import cn.hll520.linling.core.config.AppConfig;
import cn.hll520.linling.core.config.AppManageConfig;
import cn.hll520.linling.core.config.value.AppManageValue;
import cn.hll520.linling.core.object.Menu;
import cn.hll520.linling.core.object.MenuGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-21-15:35
 * @since 2021-01-21-15:35
 */
@Api(tags = "LinLingCore")
@Controller
@RequestMapping(AppConfig.APP_MANAGE_HOST)
public class AppManageMvc {

    private final AppManageConfig manageConfig;

    public AppManageMvc(AppManageConfig manageConfig) {
        this.manageConfig = manageConfig;
    }


    @ApiOperation("后台管理页")
    @GetMapping
    public String appInfoPage(Model model) {
        AppManageValue manageValue = manageConfig.getManage();
        // null 转换为 框架窗口
        for (Menu menu : manageValue.getNavbar()) {
            if (menu.getTarget() == null) {
                menu.setTarget("_view");
            }
        }
        for (MenuGroup menuGroup : manageValue.getMenu()) {
            for (Menu menu : menuGroup.getMenus()) {
                if (menu.getTarget() == null) {
                    menu.setTarget("_view");
                }
            }
        }
        model.addAttribute("app", manageValue);
        return "Manage";
    }
}
