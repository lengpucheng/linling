package cn.hll520.linling.core.autovalue;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：App 各种 Host 集合
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-01-30-17:09
 * @since 2021-01-30-17:09
 */
@ConfigurationProperties(prefix = "linling.host")
@Component()
@Data
public class AppHostValue {
    /**
     * App login 实际使用的登录页
     */
    private String appLoginHost = AppDefaultHostValue.appLoginHost;
    /**
     * App info 页面地址
     */
    private String appInfoHost = AppDefaultHostValue.appInfoHost;
    /**
     * App linling Core 默认登录页映射
     */
    private String appDefaultLoginHost = AppDefaultHostValue.appLoginHost;
    /**
     * App Linling Core 默认管理后台
     */
    private String appCoreManageHost = AppDefaultHostValue.appCoreManageHost;
    /**
     * App Swagger Api页面地址
     */
    private String appApiHost = AppDefaultHostValue.appApiHost;
    /**
     * App Druid SQL 管理页面地址
     */
    private String appSQLManageHost = AppDefaultHostValue.appSQLManageHost;
}
