package cn.hll520.linling.core.service.impl;

import cn.hll520.linling.core.object.safety.Promise;
import cn.hll520.linling.core.object.safety.Role;
import cn.hll520.linling.core.object.safety.User;
import cn.hll520.linling.core.service.LoginServer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述： 默认的 权限 控制类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-15:03
 * @since 2021-01-23-15:03
 */
@Component
public class DefaultCoreRealmServer extends AuthorizingRealm {
    @Autowired
    private LoginServer loginServer;

    /**
     * 执行登录/授权逻辑
     *
     * @param principals 参数
     * @return 授权对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 安全信息
        SimpleAuthorizationInfo safe = new SimpleAuthorizationInfo();
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Role> roles = loginServer.checkRole(user);
        // 添加身份
        if (roles != null) {
            for (Role role : roles) {
                if (role != null) {
                    safe.addRole(role.getRoleCode());
                    // 添加权限
                    List<Promise> promises = loginServer.checkPromise(role);
                    if (promises != null) {
                        for (Promise promise : promises) {
                            safe.addStringPermission(promise.getPromiseCode());
                        }
                    }
                }
            }
        }
        return safe;
    }

    /**
     * 执行认证逻辑
     *
     * @param token token 用户名和密码
     * @return 登录信息
     * @throws AuthenticationException 登录异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 将 token 转换为 UserToken
        if (token == null) {
            return null;
        }
        // 转换Token
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        // 是否输入用户名
        if (userToken.getUsername() == null) {
            return null;
        }
        // 调用login 方法
        User userinfo = loginServer.login(new User(userToken.getUsername()));
        // 判断密码
        // 参数一 是返回会 login 方法的数据  ， 参数二 是数据库的密码  参数三 是 shiro 的名字
        // shiro 会 自动 判断 密码是否一致
        return new SimpleAuthenticationInfo(userinfo, userinfo.getPassword(), "");
    }
}
