package cn.hll520.linling.core.config.value;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：AppInfo 应用信息
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-17-17:47
 * @since 2021-01-17-17:47
 */
@ConfigurationProperties(prefix = "linling.core.app")
@Component()
@Data
public class AppInfoValue {
    /**
     * 应用名称
     */
    private String appName = "Linling Core";
    /**
     * 应用描述
     */
    private String appDescription = "LinLing Core is a SpringBoot framework that have many modules and complete SQL monitor and API document";
    /**
     * 应用版本
     */
    private String appVersion = "v1.0";
    /**
     * App Host
     */
    private String appHost = "/";
    /**
     * App info 页面地址
     */
    private String appInfoHost = "/linling/core";
    /**
     * App login 页
     */
    private String appLoginHost = "/linling/login";
    /**
     * App Linling Core 管理后台
     */
    private String appCoreManageHost = "/linling/manage";
    /**
     * App Swagger Api页面地址
     */
    private String appApiHost = "/swagger-ui/index.html";
    /**
     * App Druid SQL 管理页面地址
     */
    private String appSQLManageHost = "/druid/login.html";
    /**
     * 作者
     */
    private Author appAuthor = new Author();

    @Data
    public static class Author {
        /**
         * author
         */
        private String name = "LPC";
        /**
         * 邮箱
         */
        private String email = "lpc@hll520.cn";
        /**
         * author 网站地址
         */
        private String webSite = "http://www.hll520.cn";
        /**
         * 作者网站名称
         */
        private String webName = "hll520.cn";

        /**
         * 项目git地址
         */
        private String githubUrl = "https://github.com/lengpucheng";
    }
}
