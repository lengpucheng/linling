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
 * 描述： 登录页面
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-23:00
 * @since 2021-01-23-23:00
 */
@Api(tags = "LinLingCoreMVC")
@Controller
@RequestMapping(AppDefaultHostValue.appLoginHost)
public class AppLoginMvc {
    private final AppInfoValue value;
    private final AppHostValue hostValue;

    public AppLoginMvc(AppInfoValue value, AppHostValue hostValue) {
        this.value = value;
        this.hostValue = hostValue;
    }

    @ApiOperation("登录页")
    @GetMapping()
    public String login(Model model) {
        model.addAttribute("app", value);
        model.addAttribute("host", hostValue);
        return "AppLogin";
    }
}
