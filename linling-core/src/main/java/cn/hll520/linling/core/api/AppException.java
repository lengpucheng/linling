package cn.hll520.linling.core.api;

import cn.hll520.linling.core.object.Result;
import cn.hll520.linling.core.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：异常捕获类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-19-22:04
 * @since 2021-01-19-22:04
 */
@Api(tags = "异常请求")
@ControllerAdvice
@RestController
@RequestMapping("/linling/core/error")
public class AppException {
    /**
     * 权限不足
     *
     * @param e 错误信息
     * @return 错误提示
     */
    @ApiOperation("120错误未授权")
    @RequestMapping("/120")
    @ExceptionHandler(UnauthorizedException.class)
    public Result handleShiroException(Exception e) {
        return ResultUtils.noPermit("没有权限\n" + e.getMessage());
    }

    /**
     * 未登录
     *
     * @param e 错误信息
     * @return 错误提示
     */
    @ApiOperation("110错误未登录")
    @RequestMapping("/110")
    @ExceptionHandler(AuthorizationException.class)
    public Result AuthorizationException(Exception e) {
        return ResultUtils.noLogin("请先登录\n" + e.getMessage());
    }
}
