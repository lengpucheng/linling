package cn.hll520.linling.core.service;

import cn.hll520.linling.core.object.safety.PromiseBase;
import cn.hll520.linling.core.object.safety.RoleBase;
import cn.hll520.linling.core.object.safety.UserBase;

import java.util.List;

/**
 * 描述：登录和身份验证接口
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-13:10
 * @since 2021-01-23-13:10
 */
public interface ILoginServer<T extends UserBase> {
    /**
     * 登录接口
     *
     * @param user 身份验证信息
     * @return 登录后返回的信息  失败应该返回null
     */
    T login(UserBase user);

    /**
     * 获取 用户身份所包含的身份信息
     *
     * @param user 用户身份
     * @return 身份集合信息
     */
    List<RoleBase> checkRole(UserBase user);

    /**
     * 获取身份对应的 权限信息
     *
     * @param role 身份
     * @return 权限集合
     */
    List<PromiseBase> checkPromise(RoleBase role);

    /**
     * 获取身份对应的 权限信息
     *
     * @param user 用户
     * @return 权限集合
     */
    List<PromiseBase> checkPromise(UserBase user);
}
