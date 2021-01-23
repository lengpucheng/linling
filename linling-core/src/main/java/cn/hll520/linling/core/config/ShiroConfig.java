package cn.hll520.linling.core.config;

import cn.hll520.linling.core.config.value.ShiroInfoValue;
import cn.hll520.linling.core.service.impl.DefaultCoreRealmServer;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述： shiro 组件配置
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-23-15:07
 * @since 2021-01-23-15:07
 */
@Configuration
public class ShiroConfig {
    private final ShiroInfoValue value;

    private final DefaultCoreRealmServer realm;

    public ShiroConfig(ShiroInfoValue value, DefaultCoreRealmServer realm) {
        this.value = value;
        this.realm = realm;
    }

    /**
     * 创建 ShiroFilterFactorBean
     * <p>{@code @Bean} 放入Spring 容器 使配置生效</p>
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(
            @Qualifier("SecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 配置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置拦截
        Map<String, String> filterMap = value.getFilter();
        // 如果为null 使用默认
        if (filterMap == null) {
            filterMap = new LinkedHashMap<>();
            // 放行pass -- 要在拦截前面  ** 才是所有  *是当前
            filterMap.put("/linling/core/identity/**", "anon");
            filterMap.put("/linling/core/appInfo/**", "anon");
            filterMap.put("/linling/core", "anon");  //info 页
            filterMap.put("/linling/core/**", "user");
        }
        // 设置
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        // 添加未登录跳转路径
        shiroFilterFactoryBean.setLoginUrl(value.getUnLoginUrl());
        // 添加未授权跳转路径
        shiroFilterFactoryBean.setUnauthorizedUrl(value.getUnAuthorizedUrl());

        return shiroFilterFactoryBean;
    }

    /**
     * <p>安全管理区</p>
     * 创建DefaultWebSecurityManager
     * <p>{@code @Qualifier("realm")} 找到一个名为 realm 的方法
     * 并将其注入到参数种 Spring 注解
     * </p>
     * <p>@{@code @Bean("SecurityManager")} 用于自动注入，并声明为 name=“SecurityManager”</p>
     *
     * @see Qualifier
     */
    @Bean("SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(
            @Qualifier("realm") DefaultCoreRealmServer realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 Realm
        securityManager.setRealm(realm);
        // 配置记住我
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 设置 Realm
     * <p> {@code @bean} 用于将方法放入Spring容器</p>
     */
    @Bean("realm")
    public DefaultCoreRealmServer getUserRealm() {
        // 设置缓存 使用自带的缓存
        realm.setCacheManager(new MemoryConstrainedCacheManager());
        // 开启缓存 避免频繁读取数据库  只在第一次时才会连接数据库
        realm.setCachingEnabled(value.getCaching().isCachingEnable());
        // 开启认证的缓存
        realm.setAuthenticationCachingEnabled(value.getCaching().isAuthenticationCachingEnabled());
        // 开启权限缓存
        realm.setAuthorizationCachingEnabled(value.getCaching().isAuthorizationCachingEnabled());
        // 设置 缓存名 不设置会自动取名
        realm.setAuthorizationCacheName(value.getCaching().getCacheName());

        return realm;
    }

    /*
     * ------------------------------------
     *      开  启  记   住
     * ------------------------------------
     * */

    /**
     * cookie对象;
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie(value.getRemember().getRememberCookieName());
        //httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(value.getRemember().isRememberOnlyHttp());
        // 设置域
        simpleCookie.setPath(value.getRemember().getRememberPath());
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(value.getRemember().getRememberTime());
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能,rememberMe管理器
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode(value.getRemember().getRememberBase64Encode()));
        return cookieRememberMeManager;
    }

    /*
     * ------------------------------------
     *      开  启  注   解
     * ------------------------------------
     * */

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(value.isEnableAnnotation());
        return proxyCreator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("SecurityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
