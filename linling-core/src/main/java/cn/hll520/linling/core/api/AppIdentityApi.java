package cn.hll520.linling.core.api;

import cn.hll520.linling.core.object.Result;
import cn.hll520.linling.core.object.safety.User;
import cn.hll520.linling.core.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-15:30
 * @since 2021-01-23-15:30
 */
@Api(tags = "APP身份认证")
@RestController
@RequestMapping("linling/identity")
public class AppIdentityApi {

    @ApiOperation("获取信息")
    @GetMapping
    public Result<User> identity() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Object principal = subject.getPrincipal();
            if (principal == null) {
                return ResultUtils.noLogin("未登录！");
            }
            return ResultUtils.success((User) principal);
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }

    @ApiOperation(value = "登录", notes = "必须具有username和password")
    @PostMapping("/login")
    public Result<User> login(User user, boolean remember) {
        try {
            if (user == null || user.getUsername() == null || user.getPassword() == null)
                return ResultUtils.fail(user, "用户名和密码不能为空");
            // 构造 Token
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), remember);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            // 成功后返回信息
            return ResultUtils.success((User) subject.getPrincipal());
        } catch (UnknownAccountException e) {
            return ResultUtils.fail(user, "用户不存在!\n");
        } catch (IncorrectCredentialsException e) {
            return ResultUtils.fail(user, "密码错误!\n");
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Result<Boolean> logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return ResultUtils.bool(true);
        } catch (Exception e) {
            return ResultUtils.error(e);
        }
    }
}
