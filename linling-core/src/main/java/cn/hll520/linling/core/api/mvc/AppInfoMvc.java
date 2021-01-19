package cn.hll520.linling.core.api.mvc;


import cn.hll520.linling.core.config.AppConfig;
import cn.hll520.linling.core.config.value.AppInfoValue;
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
 * @version 1.0  2021-01-19-23:08
 * @since 2021-01-19-23:08
 */
@Api(tags = "应用信息页")
@Controller
@RequestMapping(AppConfig.APP_INFO_HOST)
public class AppInfoMvc {
    private final AppInfoValue appInfoValue;

    public AppInfoMvc(AppInfoValue appInfoValue) {
        this.appInfoValue = appInfoValue;
    }

    @ApiOperation("应用信息页")
    @GetMapping
    public String appInfoPage(Model model) {
        model.addAttribute("app", appInfoValue);
        return "index";
    }
}
