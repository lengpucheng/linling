package cn.hll520.linling.core.api;

import cn.hll520.linling.core.autovalue.AppHostValue;
import cn.hll520.linling.core.autovalue.AppInfoValue;
import cn.hll520.linling.core.object.Result;
import cn.hll520.linling.core.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：APP 信息API
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-19-22:00
 * @since 2021-01-19-22:00
 */
@Api(tags = "服务信息")
@RestController
@RequestMapping("/linling/core/appInfo")
public class AppInfoApi {

    private final AppInfoValue infoValue;

    private final AppHostValue hostValue;

    public AppInfoApi(AppInfoValue infoValue, AppHostValue hostValue) {
        this.infoValue = infoValue;
        this.hostValue = hostValue;
    }

    @ApiOperation(value = "服务全部信息", notes = "info 为info host 为 host")
    @GetMapping()
    public Result<Map<String, Object>> getAppInfo() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("infos", infoValue);
            map.put("hosts", hostValue);
            return ResultUtils.success(map);
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }

    @ApiOperation("服务信息")
    @GetMapping("/info")
    public Result<AppInfoValue> infoValueResult() {
        try {
            return ResultUtils.success(infoValue);
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }

    @ApiOperation("服务相关地址")
    @GetMapping("/hosts")
    public Result<AppHostValue> hostValueResult() {
        try {
            return ResultUtils.success(hostValue);
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }

    @ApiOperation("服务版本")
    @GetMapping("/version")
    public Result<String> getAppVersion() {
        try {
            return ResultUtils.success(infoValue.getAppVersion());
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }

    @ApiOperation("服务首页")
    @GetMapping("/host")
    public Result<String> getAppHost() {
        try {
            return ResultUtils.success(infoValue.getAppHost());
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }

    @ApiOperation("服务信息页")
    @GetMapping("/infoHost")
    public Result<String> getAppInfoHost() {
        try {
            return ResultUtils.success(hostValue.getAppInfoHost());
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }
}
