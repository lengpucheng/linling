package cn.hll520.linling.core;

import cn.hll520.linling.core.autovalue.ShiroInfoValue;
import cn.hll520.linling.core.util.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 描述：通用异常拦截处理类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-26-22:59
 * @since 2021-01-26-22:59
 */
@Slf4j
@ControllerAdvice
public class LinlingCoreExceptionHandler {

    private final ShiroInfoValue value;

    public LinlingCoreExceptionHandler(ShiroInfoValue value) {
        this.value = value;
    }

    /**
     * 权限不足异常处理
     *
     * @param e 异常
     * @return 转发
     */
    @ExceptionHandler(UnauthorizedException.class)
    public String unPromise(Exception e) {
        log.error("出现错误{},未授权的访问！", e.getMessage());
        return PathUtils.forward(value.getUnAuthorizedUrl());
    }


    /**
     * 未登录异常处理
     *
     * @param e 异常
     * @return 转发
     */
    @ExceptionHandler(AuthorizationException.class)
    public String unLogin(Exception e) {
        log.error("出现错误{},未登录的访问！", e.getMessage());
        return PathUtils.redirect(value.getUnLoginUrl());
    }
}
