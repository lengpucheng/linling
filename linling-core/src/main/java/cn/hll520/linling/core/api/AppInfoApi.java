package cn.hll520.linling.core.api;

import cn.hll520.linling.core.config.value.AppInfoValue;
import cn.hll520.linling.core.object.Result;
import cn.hll520.linling.core.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：APP 信息API
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-19-22:00
 * @since 2021-01-19-22:00
 */
@Api(tags = "APP信息")
@RestController
@RequestMapping("/linling/core/appInfo")
public class AppInfoApi {

    private final AppInfoValue value;

    public AppInfoApi(AppInfoValue value) {
        this.value = value;
    }

    @ApiOperation("应用服务信息")
    @GetMapping()
    public Result<AppInfoValue> getAppInfo() {
        try {
            return ResultUtils.success(value);
        } catch (Exception e) {
            return ResultUtils.error(e);
        }

    }

    @ApiOperation("应用版本")
    @GetMapping("/version")
    public Result<String> getAppVersion() {
        try {
            return ResultUtils.success(value.getAppVersion());
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }

    @ApiOperation("应用首页")
    @GetMapping("/host")
    public Result<String> getAppHost() {
        try {
            return ResultUtils.success(value.getAppHost());
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }

    @ApiOperation("应用信息也")
    @GetMapping("/infoHost")
    public Result<String> getAppInfoHost() {
        try {
            return ResultUtils.success(value.getAppInfoHost());
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }
}
