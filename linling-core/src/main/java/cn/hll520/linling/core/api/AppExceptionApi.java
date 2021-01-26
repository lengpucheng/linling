package cn.hll520.linling.core.api;

import cn.hll520.linling.core.config.value.AppInfoValue;
import cn.hll520.linling.core.object.Result;
import cn.hll520.linling.core.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述： 处理异常请求
 * <p>对不同的请求给出了不同的响应方式，例如HTML和JSON</p>
 * <b>权限不足待更新</b>
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-26-22:59
 * @since 2021-01-26-22:59
 */
@Api(tags = "异常处理")
@Controller
@RequestMapping("/linling/error")
public class AppExceptionApi {

    private final AppInfoValue value;

    public AppExceptionApi(AppInfoValue value) {
        this.value = value;
    }

    /*
     * 未登录的异常响应
     * */
    @ApiOperation("未登录HTML")
    @RequestMapping(value = "/110", produces = MediaType.TEXT_HTML_VALUE)
    public String unLoginHtml(Model model) {
        model.addAttribute("app", value);
        return "AppLogin";
    }

    @ApiOperation("未登录JSON")
    @ResponseBody
    @RequestMapping(value = "/110", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result unLoginJson() {
        return ResultUtils.noLogin("请先登录");
    }

    /*
     * 权限不足的异常响应
     * */
    @ApiOperation("未授权HTML")
    @RequestMapping(value = "/120", produces = MediaType.TEXT_HTML_VALUE)
    public String unPromiseHtml(Model model) {
        model.addAttribute("app", value);
        return "AppInfo";
    }

    @ApiOperation("未授权JSON")
    @ResponseBody
    @RequestMapping(value = "/120", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result unPromiseJson() {
        return ResultUtils.noPermit("权限不足");
    }
}
