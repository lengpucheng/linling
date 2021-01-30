package cn.hll520.linling.core.autovalue;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述： 安全配置类
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-13:19
 * @since 2021-01-23-13:19
 */
@ConfigurationProperties(prefix = "linling.core.safety")
@Component
@Data
public class ShiroInfoValue {
    /**
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
    private List<FilterUrl> filter = null;

    /**
     * 未登录时跳转的登录页面
     */
    private String unLoginUrl = "/linling/error/110";
    /**
     * 权限不足时跳转的登录页面
     */
    private String unAuthorizedUrl = "/linling/error/120";
    /**
     * 记住我功能
     */
    private Remember remember = new Remember();
    /**
     * 缓存功能
     */
    private Caching caching = new Caching();
    /**
     * 是否开启注解
     */
    private boolean enableAnnotation = true;


    /**
     * 缓存
     */
    @Data
    public static class Caching {
        /**
         * 是否启用缓存
         */
        private boolean cachingEnable = true;
        /**
         * 是否开启认证的缓存(登录用户）
         */
        private boolean authenticationCachingEnabled = true;
        /**
         * 是否开启权限缓存
         */
        private boolean authorizationCachingEnabled = true;
        /**
         * 登录用户和权限缓存名称
         */
        private String cacheName = "LinlingCore_Safety_Cache";
    }

    /**
     * 记住我
     */
    @Data
    public static class Remember {
        /**
         * 是否启用
         */
        private boolean enable = true;
        /**
         * 记住我在前端页面的名称
         */
        private String rememberCookieName = "LinlingCore-rememberMe";
        /**
         * remember Token 是否只能由Http读取 （是否禁用JS读取Token）
         */
        private boolean rememberOnlyHttp = true;
        /**
         * remember 生效路径
         */
        private String rememberPath = "/";

        /**
         * remember 存活时间 默认30天
         */
        private int rememberTime = 30 * 24 * 60 * 60;
        /**
         * remember Base64编码key
         */
        private String rememberBase64Encode = "4AvVhmFLUs0KTA3Kprsdag==";
    }

    @Data
    public static class FilterUrl {
        /**
         * 管理的地址 不可为null
         */
        private String url;
        /**
         * 规则，默认 anon
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
        private String rule = "anon";
    }
}
