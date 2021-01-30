package cn.hll520.linling.core.api.mvc;


import cn.hll520.linling.core.autovalue.AppDefaultHostValue;
import cn.hll520.linling.core.autovalue.AppHostValue;
import cn.hll520.linling.core.autovalue.AppInfoValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：信息页
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-19-23:08
 * @since 2021-01-19-23:08
 */
@Api(tags = "LinLingCoreMVC")
@Controller
@RequestMapping(AppDefaultHostValue.appInfoHost)
public class AppInfoMvc {
    private final AppInfoValue appInfoValue;
    private final AppHostValue hostValue;

    public AppInfoMvc(AppInfoValue appInfoValue, AppHostValue hostValue) {
        this.appInfoValue = appInfoValue;
        this.hostValue = hostValue;
    }

    @ApiOperation("应用信息页")
    @GetMapping
    public String appInfoPage(Model model) {
        model.addAttribute("app", appInfoValue);
        model.addAttribute("host", hostValue);
        return "AppInfo";
    }
}
