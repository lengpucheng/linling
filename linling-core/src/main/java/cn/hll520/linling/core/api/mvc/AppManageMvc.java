package cn.hll520.linling.core.api.mvc;

import cn.hll520.linling.core.autovalue.AppDefaultHostValue;
import cn.hll520.linling.core.config.AppManageConfig;
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
@Api(tags = "LinLingCoreMVC")
@Controller
@RequestMapping(AppDefaultHostValue.appCoreManageHost)
public class AppManageMvc {

    private final AppManageConfig manageConfig;

    public AppManageMvc(AppManageConfig manageConfig) {
        this.manageConfig = manageConfig;
    }


    @ApiOperation("后台管理页")
    @GetMapping
    public String appInfoPage(Model model) {
        model.addAttribute("app", manageConfig.getManage());
        return "Manage";
    }
}
