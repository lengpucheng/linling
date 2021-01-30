package cn.hll520.linling.core.service;

import cn.hll520.linling.core.autovalue.ShiroInfoValue;

import java.util.Map;

/**
 * 描述： 待过滤路径
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-26-18:58
 * @since 2021-01-26-18:58
 */
public interface IFilterUrlServer {
    /**
     * 待过滤的路径，传入的 filter 包括了 默认的过滤器
     * <b>若返回一个new 的map 将导致默认的过滤失效</b>
     *
     * @param filter 待过滤url 键值对
     * @param value  shiro 配置参数
     * @return 过滤路径-规则
     * path,rule 按先后顺序
     * <p>
     * <b>  ** 才是全部！！！按先后顺序拦截！！不需要拦截的放在拦截前面</b>
     * <p>
     * <b>!!! 从上到下 ---- 从 细 到 粗！！！</b>
     * <p>
     * <b>一切拦截要在最下面</b>
     * <p>
     * 各默认过滤器常用如下(注意URL Pattern里用到的是两颗星,这样才能实现任意层次的全匹配)
     * <p>
     * anon             无参,表示可匿名使用,可以理解为匿名用户或游客
     * <p>
     * authc       无参,表示需认证才能使用
     * <p>
     * user ： 需要登录才可以访问，记住我起作用
     * <p>
     * role[admin] : 必须得到角色权限才可以访问（**角色授权**）
     * <p>
     * roles[admin]  参数可写多个,多个时必须加上引号,且参数之间用逗号分割,如：/admins/user/**=roles["admin,guest"]。当有多个参数时必须每个参数都通过才算通过,相当于hasAllRoles()方法
     * <p>
     * perms[user:add:*]  参数可写多个,多参时必须加上引号,且参数之间用逗号分割,如/admins/user/**=perms["user:add:*,user:modify:*"]。当有多个参数时必须每个参数都通过才算通过,相当于isPermitedAll()方法
     * <p>
     * authcBasic  无参,表示httpBasic认证
     * <p>
     * ssl         无参,表示安全的URL请求,协议为https
     */
    Map<String, String> filterUrl(Map<String, String> filter, ShiroInfoValue value);
}
